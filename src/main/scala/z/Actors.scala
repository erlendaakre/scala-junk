package z

import zio.actors._
import zio.{ExitCode, RIO, UIO, URIO, ZIO}
import zio.actors.Actor.Stateful
import zio.console.putStrLn

object Actors extends zio.App {

  sealed trait Message[+_]
  case object Reset                   extends Message[Unit]
  case object Increase                extends Message[Unit]
  case object Get                     extends Message[Int]
  case class IncreaseUpTo(n: Int) extends Message[Unit]

  val handler: Stateful[Any, Int, Message] = new Stateful[Any, Int, Message] {
    override def receive[A](
                             state: Int,
                             msg: Message[A],
                             context: Context
                           ): UIO[(Int, A)] =
      msg match {
        case Reset               => UIO((0, ()))
        case Increase            => UIO((state + 1, ()))
        case Get                 => UIO((state, state))
        case IncreaseUpTo(n)     => UIO((n, ()))
      }
  }

  val prog = for {
    system <- ActorSystem("test1")
    actor  <- system.make("actor1", Supervisor.none, 0, handler)
    _      <- actor ! Increase
    _      <- actor ! Increase
    _      <- actor ! Increase
    _      <- actor ! Increase
    _      <- actor ! Increase
    c1     <- actor ? Get
    _      <- putStrLn(s"STATE: $c1")
  } yield ()



  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = prog.exitCode
}
