package rtj.sb.fp

object Func extends App {

  // ALL scala functions are objects (Function1 - Function22)

  val strLen: (String) => Int = (s: String) => s.length
  println(strLen("oh hai mark"))

  val concat: (String, String) => String = new Function2[String, String, String] {
    override def apply(s1: String, s2: String): String = s1+s2
  }
  println(concat("Hello", "World"))

  // Function1[Int, Function1[Int, Int]]
  // (Int) => (Int => Int)
  // Int => Int => Int

  val add: Int => Int => Int = new Function1[Int, Int => Int] {
    override def apply(x: Int): Int => Int = new Function1[Int, Int] {
      override def apply(y: Int): Int = x+y
    }
  }
  println(add(5)(10)) // curried function (multiple parameter lists)

  val plus100 = add(100)
  println(plus100(50))
}
