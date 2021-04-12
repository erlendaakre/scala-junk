package rtj.sb.fp

object AnonFunc extends App {

  val double = new Function1[Int, Int] {
    override def apply(v1: Int): Int = v1*2
  }

  // lambda / anonymous function
  val double2 = (n: Int) => n * 2
  val double3: Int => Int = n => n * 2

  // lambda with multiple params
  val adder = (a: Int, b: Int) => a+b
  val adder2: (Int, Int) => Int = (a,b) => a+b // multiple params must be in brackets ()

  // without params
  val bloop = () => 42 // Unit => Int

  println(bloop)
  println(bloop()) // functions must be called with ()

  // curly braces with lambdas
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // underscore sugar can be used if type is known
  val niceIncrementer: Int => Int = _ + 1 // (x: Int) => x+1
  val niceAdder: (Int, Int) => Int = _ + _ // (a,b) => a + b

  // Int => Int => Int, Curried
  val add = (x: Int) => (y: Int) => x+y
  println(add(5)(10)) // curried function

}
