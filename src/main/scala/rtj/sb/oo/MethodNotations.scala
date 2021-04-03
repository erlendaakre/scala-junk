package rtj.sb.oo

object MethodNotations extends App {

  class Person(val name: String, favouriteMovie: String) {
    def likes(movie: String): Boolean = movie == favouriteMovie
    def hangOutWith(person: Person): String = s"$name hanging out with ${person.name}"

    def unary_! : String = name.toUpperCase + "!!!"
    def isAlive: Boolean = true

    def apply(): String = s"Hello my name is $name and I like $favouriteMovie"
  }

  val mary = new Person("Mary", "Die Hard")
  println(mary.likes("Die Hard"))
  println(mary likes "Die Hard") // infix notation

  val tom = new Person("Tom", "Pulp Fiction")

  println(mary hangOutWith tom)

  // prefix notation (unary operators)
  val x = -1
  val x2 = 1.unary_-

  // unary_ only works with - + ~ !
  println(!mary) // mary.unary_!()

  // postfix notation -- fairly useless in practice
  {
    import scala.language.postfixOps
    println(mary.isAlive)
    println(mary isAlive)
  }

  // apply
  println(mary.apply())
  println(mary()) // () on object makes compiler look for apply() in class

}
