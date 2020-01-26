import scala.util.Random

object PepsiShowdown {
  def main(args: Array[String]): Unit = {
    val name = List.fill(3)("Pepsi") ++ List.fill(3)("Cola ")
    val range = ('A' to 'F').map(_.toString)
    val rnd = name.zip(Random.shuffle(range))
    println("\n\nPress enter to start Pepsi Max Cherry vs Tesco Cola Cherry ultimate showdown randomized double blinded controlled trial of the century(tm)!")
    System.in.read()
    rnd.map(t => s"${t._1}  ---->  ${t._2}").foreach(println)
  }
}
