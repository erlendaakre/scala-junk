package io.aakre.junk

import scala.annotation.tailrec

object VarLenEncoding extends App {
  val testData = List(("", ""), ("XYZ", "XYZ"), ("AAABC", "A3BC"), ("XXXX", "X4"))

  def encode(s: String): String = split(s).map(f => if (f.length == 1) f else s"${f.head}${f.length}").mkString

  def decode(s: String): String = ??? // expand(s)

  @tailrec private def split(s: String, acc: List[String] = Nil): List[String] = {
    if (s.isEmpty) Nil
    else {
      val subArray = s.takeWhile(_ == s(0))
      if (s.length - subArray.length == 0) acc ++ List(subArray)
      else split(s.substring(subArray.length), acc ++ List(subArray))
    }
  }

//  private def expand(s: String, acc: String = ""): String = {
//    if(s.isEmpty) acc
//    else {
//      val pos = findFirstNumber(s)
//      if(s.isEmpty) acc
//      else if(pos.isDefined) {
//        val number = s.substring()
//          expand(s.substring(pos.get._2), acc + s.substring(pos.get._1, )
//      }
//      else expand()
//
//      ""
//    }
//  }

  private def getNumberFromString(s: String, a: Int, b: Int): Option[Int] = ???

  type EndPosition = Int
  type StartPosition = Int
  private def findFirstNumber(s: String): Option[(StartPosition, EndPosition)] = {
    if(s.isEmpty) None
    else {
      var i = 0
      var nStart = -1
      var nEnd = -1
      var notFound = true
      while (i < s.length && notFound) {
        if (s(i).isDigit) {
          if (nStart < 0) nStart = i
          if (nEnd < 0) nEnd = i
        }
        else {
          if (nStart > 0) {
            nEnd = i
            notFound = false
          }
        }
        i += 1
      }
      Some(nStart,nEnd)
    }
  }

  val x = findFirstNumber("aaaaaaaaaa42bbbbbbb")
  println(x)

  testData.foreach { t =>
    println(s"encoded: ${encode(t._1)}, expected: ${t._2}")
    assert(encode(t._1) == t._2)
  }
}
