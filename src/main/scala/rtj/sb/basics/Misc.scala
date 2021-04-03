package rtj.sb.basics

import scala.annotation.tailrec

object Misc extends App {
  def cbv(n: Long): Unit = {
    println("by value " + n)
    println("by value " + n)
  }

  // n: => T        call by name
  def cbn(n: => Long): Unit = {
    println("by name " + n)
    println("by name " + n)
  }

  cbv(System.nanoTime())
  println
  cbn(System.nanoTime())

  @tailrec
  def fac(n: Int, acc: Int = 1): Int = {
    if(n <= 1) acc
    else fac(n-1, acc*n)
  }

  val fac10 = fac(10)

}
