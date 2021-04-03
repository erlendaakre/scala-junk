package rtj.sb.oo

object basic extends App {

  val erlend = new Person("Erlend", 38)
  val picard = new Person("Picard", 78)
  println(erlend.age)
  erlend.greet(picard)

  val cs = new Writer("Carl", "Sagan", 1934)
  val dhw = new Novel("Demon haunted world", 1967, cs)

  println(dhw.authorAge)

  val counter = new Counter(0)
  println(counter.inc.inc.inc.count)
}

class Writer(val firstname: String, val lastname: String, val yearOfBirth: Int) {
  def fullName: String = firstname + " " + lastname
}

class Novel(val name: String, val yearOfRelease: Int, author: Writer) {
  def authorAge: Int = yearOfRelease - author.yearOfBirth
  def isWrittenBy(w: Writer): Boolean = w == author
  def copy(newRelease: Int): Novel = new Novel(name, newRelease, author)
}

class Counter(n: Int) {
  def count: Int = n
  def inc = new Counter(n+1)
  def dec = new Counter(n-1)
  def inc(offset: Int) = new Counter(n+offset)
  def dec(offset: Int) = new Counter(n-offset)
}


// Note class parameters are not fields. person.name does not work, but person.age does
class Person(val name: String, val age:Int = 5) {
  val x = 56

  println(s"created a person named $name")

  def greet(p: Person): Unit = println(s"$name: Hi there ${p.name}")

  // multiple constructors
  def this(name: String) = this(name, 0)
}
