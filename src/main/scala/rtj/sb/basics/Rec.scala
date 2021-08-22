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
    def go(nn: Long, acc: BigInt): BigInt =
      if(nn <= 1) acc
      else go(nn-1, acc*nn)

    go(n, 1)
  }

  println(factorial(10))
  println(factorial2(10))

  println(factorial2(5000))


  def concat(s: String, n:Int): String = {
    @tailrec
    def go(i: Int, acc: String): String = {
      if(i <= 0) acc
      else go(i-1, acc+s)
    }
    go(n,"")
  }

  println(concat("lol",3))

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

  (2 to 5000).foreach { n =>
    assert(isPrime(n) == ExprFunc.isPrime(n))
  }

  def fib(n: Int): Long = {
    @tailrec
    def go(i: Int, previous: Long, current: Long): Long = {
      if (i >= n) current
      else go(i+1,current, current+previous)
    }

    if( n < 1) 0
    else if(n == 2) 1
    else go(2, 1,1)
  }

  assert(fib(0) == 0)
  assert(fib(1) == 1)
  assert(fib(2) == 1)
  assert(fib(3) == 2)
  assert(fib(5) == 5)
  assert(fib(7) == 13)
  assert(fib(8) == 21)
  assert(fib(15) == 610)

}
