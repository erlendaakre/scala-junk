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

  // Curried
  def nTimesCurried(f: Int => Int, n: Int): (Int => Int) =
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesCurried(f, n - 1)(f(x))

  val plusTen = nTimesCurried(plusOne, 10)
  println(plusTen(20))

  val curriedAdd: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  val add5 = curriedAdd(5)
  println(add5(3))
  println(curriedAdd(10)(7))

  // functions with multiple parameter lists
  def curriedFormat(format: String)(x: Double) = format.format(x)

  val stdFormat: (Double => String) = curriedFormat("%1.2f")
  val preciseFormat: (Double => String) = curriedFormat("%1.8f")

  println(stdFormat(Math.PI))
  println(preciseFormat(Math.PI))


}
