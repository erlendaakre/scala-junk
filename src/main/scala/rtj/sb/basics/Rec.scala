package rtj.sb.basics

import scala.annotation.tailrec

object Rec extends App {

  def factorial(n: Long): Long = {
    if (n <= 1) 1
    else {
      println(s"factorial of $n")
      val res = n * factorial(n - 1)
      println(s"factorial of $n computed to $res")
      res
    }
  }

  def factorial2(n: Long): BigInt = {
    @tailrec
    def go(acc: BigInt, nn: Long): BigInt =
      if(nn <= 1) acc
      else go(acc*nn, nn-1)

    go(1, n)
  }

  println(factorial(10))
  println(factorial2(10))

  println(factorial2(5000))


}
