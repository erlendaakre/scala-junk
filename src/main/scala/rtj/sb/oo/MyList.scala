package rtj.sb.oo

import rtj.sb.oo.Generics.MyList

// list of Integers
abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  // if adding B (Supertype of A) MyList also becomes of type parameter B
  def add[B >: A](e: B): MyList[B]
  def printElements: String
  override def toString: String = "[" + printElements + "]"

  def filter(p: MyPredicate[A]): MyList[A]
  def map[B](t: MyTransformer[A, B]): MyList[B]

  def flatMap[B](t: MyTransformer[A,MyList[B]]): MyList[B]
  def ++[B >: A](list: MyList[B]): MyList[B] // list concat
}

// Nothing is at bottom of class hierarchy and is a valid substitute for ANY type
// As nothing is a substitute for any type, Empty here should also be a substitute for
 // a MyList[Any Type]
case object Empty extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException
  override def tail: MyList[Nothing] = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  // B must be supertype of Nothing (i.e. any class)
  override def add[B >: Nothing](e: B): MyList[B] = new Cons(e, Empty)
  def printElements: String = ""

  def filter(p: MyPredicate[Nothing]): MyList[Nothing] = Empty
  def map[B](t: MyTransformer[Nothing, B]): MyList[B] = Empty
  def flatMap[B](t: MyTransformer[Nothing,MyList[B]]): MyList[B] = Empty
  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  override def head: A = h
  override def tail: MyList[A] = t
  override def isEmpty: Boolean = false
  override def add[B >: A](e: B): MyList[B] = new Cons(e, this)
  def printElements: String =
    if(t.isEmpty) "" + h
    else h + " " + t.printElements

  override def filter(p: MyPredicate[A]): MyList[A] = {
    if(p.test(h)) new Cons(h, t.filter(p))
    else t.filter(p)
  }

  override def map[B](tr: MyTransformer[A, B]): MyList[B] =
    Cons(tr.transform(h), t.map(tr))

  def ++[B >: A](list: MyList[B]): MyList[B] =
    Cons(h, t ++ list)

  override def flatMap[B](tr: MyTransformer[A, MyList[B]]): MyList[B] =
    tr.transform(h) ++ t.flatMap(tr)
}



trait MyPredicate[-T] {
  def test(t: T): Boolean
}
trait MyTransformer[-A, B] {
  def transform(a: A): B
}

case object EvenPredicate extends MyPredicate[Int] {
  override def test(t: Int): Boolean = t % 2 == 0
}

case object StrLenTransformer extends MyTransformer[String, Int] {
  override def transform(a: String): Int = a.length
}

object ListTest extends App {
  val listOfInts: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val listOfInts2: MyList[Int] = new Cons(5, new Cons(6, Empty))
  val listOfStrings: MyList[String] = new Cons("Hi", new Cons("there", Empty))

  println(listOfInts)
  println(listOfStrings)

  println(listOfInts.map(new MyTransformer[Int, Int] {
    override def transform(a: Int): Int = a*2
  }))
  // or
  println(listOfInts.map((a: Int) => a * 2))


  println(listOfInts.filter(EvenPredicate))

  println(listOfInts ++ listOfInts2)

  println(listOfInts.flatMap(new MyTransformer[Int, MyList[Int]] {
    override def transform(a: Int): MyList[Int] = new Cons(a, new Cons(a*10, Empty))
  }))
}