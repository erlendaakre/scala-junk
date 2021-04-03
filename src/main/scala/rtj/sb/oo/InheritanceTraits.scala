package rtj.sb.oo

object InheritanceTraits extends App {

  // single inheritance
  sealed class Animal {
    val creatureType = "wild"
    def eat(): Unit = println("nom nom")
  }
  class Cat extends Animal
  val cat = new Cat
  cat.eat()
  println()

  // constructors
  class Person(name: String, age: Int)
  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)

  class Dog extends Animal {
    override val creatureType: String = "domesticated"
    override def eat(): Unit = {
      super.eat()
      println("crunch crunch")
    }
  }
  val dog = new Dog
  dog.eat()

  // type substitution (polymorphism)
  val unknownAnimal: Animal = new Dog()
  unknownAnimal.eat

  // prevent subclass overrides with final on member or entire class
  // or sealed (allowed to extend class in THIS file)
}
