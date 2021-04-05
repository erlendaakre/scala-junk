package rtj.sb.oo

// list of Integers
abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  // if adding B (Supertype of A) MyList also becomes of type parameter B
  def add[B >: A](e: B): MyList[B]
  def printElements: String
  override def toString: String = "[" + printElements + "]"
}

// Nothing is at bottom of class hierarchy and is a valid substitute for ANY type

// As nothing is a substitute for any type, Empty here should also be a substitute for
 // a MyList[Any Type]
object Empty extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException
  override def tail: MyList[Nothing] = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  // B must be supertype of Nothing (i.e. any class)
  override def add[B >: Nothing](e: B): MyList[B] = new Cons(e, Empty)
  def printElements: String = ""
}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  override def head: A = h
  override def tail: MyList[A] = t
  override def isEmpty: Boolean = false
  override def add[B >: A](e: B): MyList[B] = new Cons(e, this)
  def printElements: String =
    if(t.isEmpty) "" + h
    else h + " " + t.printElements
}

object ListTest extends App {
  val listOfInts: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val listOfStrings: MyList[String] = new Cons("Hi", new Cons("there", Empty))

  println(listOfInts)
  println(listOfStrings)
}