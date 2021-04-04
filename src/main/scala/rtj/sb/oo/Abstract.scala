package rtj.sb.oo

object Abstract extends App {

  // abstract = unimplemented fields/methods
  abstract class Animal {
    val creatureType: String = "wild" // non-abstract
    def eat(): Unit // abstract
  }

  class Dog extends Animal {
    // override keyword optional for abstract classes
    override val creatureType: String = "canine"
     def eat(): Unit = println("crunch crunch")
  }

  // Trait
  trait Carnivore {
    def eat(animal: Animal): Unit
    val preferredMeal: String = "fresh meat"
  }

  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc"

    override def eat(): Unit = println("nom nom nom")
    override def eat(animal: Animal): Unit = println(s"Croc eating an ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile

  dog.eat()
  croc.eat(dog)

  // traits vs abstract classes
  // 1 = traits do not have constructor parameters // trait Animal(val name)
  // 2 = multiple traits can be inherited by same class
  // 3 = traits = behavior, abstract class = "thing"
}
