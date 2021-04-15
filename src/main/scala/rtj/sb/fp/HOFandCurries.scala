package rtj.sb.fp

import scala.annotation.tailrec

object HOFandCurries extends App {

  // HOF - Higher Order Functions
//   val superFunc: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = ???

  // function that applies function n times to a value x
  // nTimes(f,n,x)
  @tailrec
  def nTimes(f: Int => Int, n: Int, x: Int): Int = {
    if(n <= 0) x
    else nTimes(f, n-1, f(x))
  }

  val plusOne = (n: Int) => n+1
  println(nTimes(plusOne, 5, 10))

  // Curried
  def nTimesCurried(f: Int => Int, n: Int): (Int => Int) =
    if(n <= 0) (x:Int) => x
    else (x:Int) => nTimesCurried(f, n-1)(f(x))

  val plusTen = nTimesCurried(plusOne, 10)
  println(plusTen(20))
}
