package com.fdilke.musicxml


case class EuclideanRhythm(
  numOnsets: Int,
  cycleLength: Int
) {
  val intervals: Seq[Int] =
    EuclideanRhythm.calculateIntervals(numOnsets, cycleLength)

  lazy val partialSums: Seq[Int] =
    intervals.scanLeft(0)(_ + _)

  def string(
    rotate: Int,
    repeat: Int,
    beatChar: Char
  ): String = {
    val baseCycle =
      (0 until cycleLength).map { i =>
        if (partialSums.contains(i))
          beatChar
        else
          EuclideanRhythm.REST_CHAR
      }.mkString("")

    val rotatedCycle =
      baseCycle.substring(rotate) + baseCycle.substring(0,rotate)

    rotatedCycle * repeat
  }
}

object EuclideanRhythm {
  val REST_CHAR = '.'

  def calculateIntervals(k: Int, n: Int): Seq[Int] =
    if (n % k == 0) {
      Seq.fill(k)(n/k)
    } else {
      val a = n % k
      val subRhythm = EuclideanRhythm.calculateIntervals(a, k)
      val floor = n/k
      val ceiling = floor + 1
      subRhythm.flatMap { x =>
        Seq.fill(x - 1)(floor) :+ ceiling
      }
    }
}
