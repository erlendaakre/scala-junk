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


  def concat(s: String, n:Int): String = {
    @tailrec
    def go(i: Int, acc: String): String = {
      if(i <= 1) acc
      else go(i-1, acc+s)
    }
    go(n,s)
  }

  println(concat("lol",15))

  def isPrime(n: Int): Boolean = {
    @tailrec
    def go(nn: Int, stillPrime: Boolean): Boolean = {
      if(!stillPrime) false
      else if(nn <= 1) true
      else go(nn-1, n % nn != 0 && stillPrime)
    }
    go(n/2, true)
  }

  println(isPrime(2003))
  println(isPrime(125))



  def fib(n: Int): Long = {
    @tailrec
    def go(i: Int, previous: Long, current: Long): Long = {
      if (i >= n) current
      else go(i+1,current, current+previous)
    }

    go(3, 1,2)
  }

  println(fib(8))

}
