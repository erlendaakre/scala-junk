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

  val str = "hello world, squirrels are attacking soon"
  println(str.split(" ").toList.map(_.reverse))

  val nStr = "5"
  val n = nStr.toInt
  val name = "Kirk"
  val speed = 1.786f

  println(s"number $n")
  println(f"$name%s can go $speed%2.2f LY every $n%d hours")

  println(raw"test \n\n\n foo")

}
