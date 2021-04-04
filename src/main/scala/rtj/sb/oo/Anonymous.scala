package rtj.sb.oo

object Anonymous extends App {

  abstract class Animal {
    def eat(): Unit
  }

  // anonymous class
  val funnyAnimal: Animal = new Animal {
    override def eat(): Unit = println("whaaah whaaaaa!")
  }

  println(funnyAnimal.getClass)

  class Person(name: String) {
    def sayHi():Unit = println(s"Hi I'm $name")
  }

  val jim = new Person("Jim") {
    override def sayHi(): Unit = println("derrrrrrr I'm Jim")
  }
}
