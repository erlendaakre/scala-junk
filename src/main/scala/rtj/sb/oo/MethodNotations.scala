package rtj.sb.oo

object MethodNotations extends App {

  class Person(val name: String, favouriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = movie == favouriteMovie
    def +(person: Person): String = s"$name hanging out with ${person.name}"
    def +(nickName: String): Person = new Person(s"$name ($nickName)", favouriteMovie, age)
    def unary_+ : Person = new Person(name, favouriteMovie, age + 1)
    def unary_! : String = name.toUpperCase + "!!!"
    def isAlive: Boolean = true

    def learns(skill: String) = s"$name learns $skill"
    def learnsScala: String = this learns "Scala"
    def apply(): String = s"Hello my name is $name and I like $favouriteMovie"
    def apply(n: Int): String = s"$name watched $favouriteMovie $n times"
  }

  val mary = new Person("Mary", "Die Hard")
  println(mary.likes("Die Hard"))
  println(mary likes "Die Hard") // infix notation

  val tom = new Person("Tom", "Pulp Fiction")

  println(mary + tom)

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

  println((mary + "The Rockstar")())
  println(s"Age:  ${mary.age} => ${(+mary).age} ")

  {
    import scala.language.postfixOps
    println(mary learnsScala)
  }

  println(mary(45))

}
