package rtj.sb.basics

import scala.annotation.tailrec

object ExprFunc extends App {
  println(100 >>> 2)

  // code block
  val b = {
    val x = 5
    val y = 9000

    if(x > 5) "foo" else "bar"
  }

  def f(n:Int): Int = (n*5)/2
  println(f(5))

  def foo(): Int = 50

  def prettyPrint(): Unit = {
    val v = f(foo())
    def pretty(n: Int) = s"*$n*"
    println(pretty(v))
  }
  prettyPrint()

  def repeat(s: String, n: Int): String = {
    if(n <= 1) s
    else s + repeat(s, n-1)
  }
  println(repeat("shit! ", 10))

  def fac(n: Int): Long = {
    @tailrec
    def go(acc: Long, i: Int): Long =
      if(i <= 0) acc
      else go(acc*i , i-1)
    go(1, n)
  }
  println(fac(5))
  assert(fac(1) == 1)
  assert(fac(2) == 2)
  assert(fac(3) == 6)
  assert(fac(5) == 120)
  assert(fac(8) == 40320)
  assert(fac(12) == 479001600)

  def fib(n: Int): Long = {
    if(n <= 2) 1
    else fib(n-1) + fib(n-2)
  }
  println(fib(7))

  def isPrime(n: Long) = {
    assert(n >= 1, "isPrime only take positive integers")
    @tailrec
    def go(i: Int): Boolean = {
      if(i > n/2) true
      else {
        if(n % i == 0) false
        else go(i+1)
      }
    }
    go(2)
  }

  println(isPrime(5))
  assert(isPrime(3))
  assert(!isPrime(4))
  assert(isPrime(5))
  assert(!isPrime(6))
  assert(!isPrime(21))
  assert(isPrime(47))
  assert(!isPrime(48))
  assert(!isPrime(606))
  assert(isPrime(607))
}
