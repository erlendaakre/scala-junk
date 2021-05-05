package z

import zio.{ExitCode, Has, Task, URIO, ZIO, ZLayer}

object RtjZLayer extends zio.App {

  case class User(name: String, email: String)

  object UserEmailer {
    type UserEmailerEnv = Has[UserEmailer.Service]

    // service definition
    trait Service {
      def notify(user: User, message: String): Task[Unit]
    }

    // service implementation
    val live: ZLayer[Any, Nothing, UserEmailerEnv] = ZLayer.succeed(new Service {
      override def notify(user: User, message: String): Task[Unit] =
        Task(println(s"[UserEmailer] sending $message to $user"))
    })

    // front-facing API
    def notify(user: User, message: String): ZIO[UserEmailerEnv, Throwable, Unit] = {
      ZIO.accessM(hasService => hasService.get.notify(user, message))
    }
  }

  object UserDb {
    type UserDbEnv = Has[UserDb.Service]

    trait Service {
      def insert(user: User): Task[Unit]
    }

    val live = ZLayer.succeed(new Service {
      override def insert(user: User): Task[Unit] = Task {
        println(s"[Database] inserting $user")
      }
    })

    def insert(user: User): ZIO[UserDbEnv, Throwable, Unit] =
      ZIO.accessM(_.get.insert(user))
  }

  // ---------------------------------- ZLayer Composition ----------------------------------
  private val picard = User("Picard", "picard@starfleet.ufp")

  // Horizontal composition
  // ZLayer[In1, E1, Out1] ++ ZLayer[In2, E2, Out2] =>
  //      ZLayer[In1 with In2, E1 with E2 (super of E1 and E2), Out1 with Out2]
  import UserDb._
  import UserEmailer._
  val userBackendLayer: ZLayer[Any, Nothing, UserDbEnv with UserEmailerEnv] = UserDb.live ++ UserEmailer.live

  val horizontalComposition = {
    UserEmailer.notify(picard, "red alert!") // the kind of effect
      //.provideLayer(UserEmailer.live) // provide input for that effect (DI)
      .provideLayer(userBackendLayer)
  }

  // Vertical composition
  object UserSubscription {
    type UserSubscriptionEnv = Has[UserSubscription.Service]
    class Service(notifier: UserEmailer.Service, db: UserDb.Service) {
      def subscribe(user: User): Task[User] =
        for {
          _ <- db.insert(user)
          _ <- notifier.notify(user, s"Welcome ${user.name}")
        } yield user
    }

    val live: ZLayer[UserEmailerEnv with UserDbEnv, Nothing, UserSubscriptionEnv] =
      ZLayer.fromServices[UserEmailer.Service, UserDb.Service, UserSubscription.Service] {
        (userEmailer, userDb) => new Service(userEmailer, userDb)
      }

    def subscribe(user: User): ZIO[UserSubscriptionEnv, Throwable, User] =
      ZIO.accessM(_.get.subscribe(user))
  }

  import UserSubscription._
  // plug output of userbackendlayer into requirement/input for UserSubscription
  val userSubscriptionLayer: ZLayer[Any, Nothing, UserSubscriptionEnv] = userBackendLayer >>> UserSubscription.live



  val verticalComposition = {
    UserSubscription.subscribe(picard)
      .provideLayer(userSubscriptionLayer)
  }

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = verticalComposition.exitCode

}
