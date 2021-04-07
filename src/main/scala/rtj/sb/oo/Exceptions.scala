package rtj.sb.oo

object Exceptions extends App {

  def getInt(ex: Boolean = false): Int = if(ex) throw new RuntimeException("shit!") else 7

  // try
  val t = try {
    getInt(true)
  } catch {
    case e: RuntimeException => println("oups"); 9000
  }
  finally {
    // optional and does not affect return type of 'try'
    // only use for side effects
    println("finally")
  }
  val x = Int.MaxValue + Int.MaxValue
  println(s"t = $t x = $x")
}
