package rtj.sb.fp

import scala.annotation.tailrec

object HOFandCurries extends App {

  // HOF - Higher Order Functions
  //   val superFunc: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = ???

  // function that applies function n times to a value x
  // nTimes(f,n,x)
  @tailrec
  def nTimes(f: Int => Int, n: Int, x: Int): Int = {
    if (n <= 0) x
    else nTimes(f, n - 1, f(x))
  }

  val plusOne = (n: Int) => n + 1
  println(nTimes(plusOne, 5, 10))

  // nTimes returns Int => Int function
  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) =
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n - 1)(f(x))

  val plusTen = nTimesBetter(plusOne, 10)
  println(plusTen(20))

  val curriedAdd: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  val add5 = curriedAdd(5)
  println(add5(3))
  println(curriedAdd(10)(7))

  // Currying: functions with multiple parameter lists
  def curriedFormat(format: String)(x: Double) = format.format(x)

  val stdFormat: (Double => String) = curriedFormat("%1.2f")
  val preciseFormat: (Double => String) = curriedFormat("%1.8f")

  println(stdFormat(Math.PI))
  println(preciseFormat(Math.PI))


  println("================ EXERCISES ==============")
  def toCurry(f: (Int, Int) => Int): Int => Int => Int = (x: Int) => (y: Int) => f(x,y)

  def fromCurry(f: Int => Int => Int): (Int,Int) => Int = (a: Int, b:Int) => f(a)(b)

  val toCurryTest = toCurry((a: Int, b: Int) => a+b)
  println(toCurryTest(7)(11))

  val fromCurryTest = fromCurry(toCurryTest)
  println(fromCurryTest(20, 8))


  // TODO
  def compose() = ???


  println("=============== BONUS ====================")

  def toCurryF[A](f: (A, A) => A): A => A => A = (x: A) => (y: A) => f(x,y)
  def fromCurryF[A](f: A => A => A): (A,A) => A = (a: A, b:A) => f(a)(b)

  val toCurryFTest = toCurryF[String]((a: String, b: String) => a + " " + b)
  println(toCurryFTest("Hello")("Curry"))

  val fromCurryFTest = fromCurryF(toCurryFTest)
  println(fromCurryFTest("Curry", "Hellooo!"))



}
