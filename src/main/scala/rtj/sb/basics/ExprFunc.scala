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

  def print(): Unit = {
    val v = f(foo())
    def pretty(n: Int) = s"*$n*"
    println(pretty(v))
  }
  print()

  def fac(n: Int): Long = {
    @tailrec
    def go(acc: Long, nn: Int): Long =
      if(nn == n) acc*n
      else go(acc*nn , nn+1)
    go(1, 1)
  }
  println(fac(5))

  def fib(n: Int): Long = {
    if(n <= 2) 1
    else fib(n-1) + fib(n-2)
  }
  println(fib(7))
}
