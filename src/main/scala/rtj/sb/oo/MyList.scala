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

  def filter(p: A => Boolean): MyList[A]
  def map[B](t: A => B): MyList[B]

  def flatMap[B](t: A => MyList[B]): MyList[B]
  def ++[B >: A](list: MyList[B]): MyList[B] // list concat

  def foreach(f: A => Unit): Unit
  def sort(sorting: (A, A) => Int): MyList[A]
  def zipWith[B >: A](other: MyList[B], zipF: (A, B) => MyList[B]): MyList[B] // TODO check variance
  def fold[B >: A](initial: B)(f: (A,B) => B): B // TODO check
}

// Nothing is at bottom of class hierarchy and is a valid substitute for ANY type
// As nothing is a substitute for any type, Empty here should also be a substitute for
 // a MyList[Any Type]
case object Empty extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException
  override def tail: MyList[Nothing] = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  // B must be supertype of Nothing (i.e. any class)
  override def add[B >: Nothing](e: B): MyList[B] = Cons(e, Empty)
  def printElements: String = ""

  def filter(p: Nothing => Boolean): MyList[Nothing] = Empty
  def map[B](t: Nothing => B): MyList[B] = Empty
  def flatMap[B](t: Nothing => MyList[B]): MyList[B] = Empty
  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  override def foreach(f: Nothing => Unit): Unit = ()
  override def sort(sorting: (Nothing, Nothing) => Int): MyList[Nothing] = Empty
  override def zipWith[B >: Nothing](other: MyList[B], zipF: (Nothing, B) => MyList[B]): MyList[B] = Empty
  override def fold[B >: Nothing](initial: B)(f: (Nothing, B) => B): B = ??? // TODO FIX ME
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  override def head: A = h
  override def tail: MyList[A] = t
  override def isEmpty: Boolean = false
  override def add[B >: A](e: B): MyList[B] = Cons(e, this)
  def printElements: String =
    if(t.isEmpty) "" + h
    else h + " " + t.printElements

  override def filter(p: A => Boolean): MyList[A] = {
    if(p(h)) Cons(h, t.filter(p))
    else t.filter(p)
  }

  override def map[B](tr: A => B): MyList[B] =
    Cons(tr(h), t.map(tr))

  def ++[B >: A](list: MyList[B]): MyList[B] =
    Cons(h, t ++ list)

  override def flatMap[B](tr: A => MyList[B]): MyList[B] =
    tr(h) ++ t.flatMap(tr)

  override def foreach(f: A => Unit): Unit = ???
  override def sort(sorting: (A, A) => Int): MyList[A] = ???
  override def zipWith[B >: A](other: MyList[B], zipF: (A, B) => MyList[B]): MyList[B] = ???
  override def fold[B >: A](initial: B)(f: (A, B) => B): B = ??? // TODO FIX ME
}

case object StrLenTransformer extends ((String) => Int) {
  override def apply(v1: String): Int = v1.length
}

object ListTest extends App {
  val listOfInts: MyList[Int] =  Cons(1, Cons(2, Cons(3, Empty)))
  val listOfInts2: MyList[Int] =  Cons(5, Cons(6,Empty))
  val listOfStrings: MyList[String] =  Cons("Hi",  Cons("there", Empty))

  println(listOfInts)
  println(listOfStrings)

  // or
  println(listOfInts.map(_ * 2))

  val evenPredicate = (n: Int) => n % 2 == 0

  println(listOfInts.filter(evenPredicate))
  println(listOfInts ++ listOfInts2)

  println(listOfInts.flatMap(a =>  Cons(a, Cons(a*10, Empty))))
}