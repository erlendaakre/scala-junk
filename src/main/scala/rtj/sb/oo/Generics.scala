package rtj.sb.oo

object Generics extends App {

  // Generic List with type parameter A
  class MyList[+A] {
    // if adding supertype of A (B), resulting List is of B
    def add[B >: A](e: B): MyList[B] = ???
  }

  // Generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }

  // variance
  // ===========================
  class LifeForm
  class Animal extends LifeForm
  class Cat extends Animal
  class Dog extends Animal
  class Bagel

  // COVARIANCE - List[Cat] extends List[Animal]
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog) ??? what happens? we return list of Animals (def add[B >: A](e: B): MyList[B])

  // INVARIANCE - List[Cat] does NOT extend List[Animal]
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]
  // val illegal: InvariantList[Animal] = new InvariantList[Cat]

  // CONTRAVARIANCE
  class ContravariantList[-A]
  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]

  // trainer of Animal can be trainer of Cat
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]



  // bounded types
  // ===========================
  // Upper bounded type: Cage only accepts type parameter (A) of subtype Animal (or Animal)
  class Cage[A <: Animal](animal: A)
  val cage = new Cage(new Dog)
//  val illegal = new Cage(new LifeForm)

  // Lower bounded type: Cage only accept superTypes of Animal as type param
  class Cage2[A >: Animal](animal: A)
  val cage2 = new Cage2(new LifeForm)
  val illegal2 = new Cage2(new Dog) // Legal as Dog is Animal and compiler can make fit that in Cage[Animal]
//  val reallyIllegal: Cage[Dog] = new Cage2(new Dog)
}
