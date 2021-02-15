package io.aakre.junk.dice

import scala.util.Random

/*
 * Junky dice rolling
 */
object Dice {

  final case class Dice(min: Int, max: Int) { self =>
    def +(that: Dice): Dice = Dice(self.min + that.min, self.max + that.max)

    override def toString = s"[D${if (min == 1) "" else s"$min-" }$max]"
  }

  object Dice {
    val D4: Dice  = Dice(1, 4)
    val D6: Dice  = Dice(1, 6)
    val D12: Dice = Dice(1, 12)
    val D20: Dice = Dice(1, 20)
  }

  final case class DiceResult(dice: Dice, value: Int) { self =>
    def +(that: DiceResult): DiceResult = DiceResult(self.dice + that.dice, self.value + that.value)
    override def toString = s"$dice $value"
  }

  final case class Roll(dice: Dice, run: () => DiceResult) { self =>
    def +(that: Roll): Roll = Roll(self.dice + that.dice, () => self.run() + that.run())
    def result: DiceResult = self.run()
  }

  object Roll {
    def roll(d: Dice): DiceResult = {
      val random = Random.between(d.min, d.max)
      Roll(d, () => DiceResult(d, random)).result
    }
    def roll(ds: List[Dice]): List[DiceResult] = ds.map(roll)
  }

  def main(args: Array[String]): Unit = {
    import Dice._

    println(D6)
    println(D6 + D6)

    val r1 = Roll.roll(D20)
    println(r1)
    val r2 = Roll.roll(D6 + D6 + D6)
    println(r2)

    val l1 = List(D4, D6, D12, D20)
    println(l1)
    val rs = Roll.roll(l1)
    println(rs.reduce(_ + _))
  }
}
