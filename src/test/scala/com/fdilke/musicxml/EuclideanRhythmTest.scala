package com.fdilke.musicxml

import org.scalatest.FreeSpec
import org.scalatest.Matchers._

class EuclideanRhythmTest extends FreeSpec {

  "EuclideanRhythm" - {
    "calculates correctly for small values" in {
      EuclideanRhythm.calculateIntervals(1,2) shouldBe Seq(2)
      EuclideanRhythm.calculateIntervals(2,3) shouldBe Seq(1,2)
      EuclideanRhythm.calculateIntervals(2,4) shouldBe Seq(2,2)
      EuclideanRhythm.calculateIntervals(3,5) shouldBe Seq(2,1,2)
      EuclideanRhythm.calculateIntervals(7, 16) shouldBe Seq(2,2,3,2,2,2,3)
    }

    "obeys basic sanity tests" in {
      for {
        n <- 2 to 17
        k <- 1 to n
      } {
        val rhythm = EuclideanRhythm.calculateIntervals(k, n)
        rhythm.size shouldBe k
        rhythm.sum shouldBe n
        val f = n /  k
        rhythm.forall { r =>
          (r == f) || (r == f + 1)
        } shouldBe true
      }
    }

    "can rotate, repeat and express the result as a string" in {
      EuclideanRhythm(2,3).string(0,1,'#') shouldBe "##."
      EuclideanRhythm(3,5).string(0,1,'*') shouldBe "*.**."
      EuclideanRhythm(3,5).string(0,2,'*') shouldBe "*.**.*.**."
      EuclideanRhythm(7,16).string(0,1,'*') shouldBe "*.*.*..*.*.*.*.."
      EuclideanRhythm(7,16).string(1,1,'*') shouldBe ".*.*..*.*.*.*..*"
      EuclideanRhythm(7,16).string(2,1,'*') shouldBe "*.*..*.*.*.*..*."
      EuclideanRhythm(7,16).string(4,1,'*') shouldBe "*..*.*.*.*..*.*."
      EuclideanRhythm(7,16).string(4,3,'%') shouldBe
        "%..%.%.%.%..%.%.%..%.%.%.%..%.%.%..%.%.%.%..%.%."
    }
  }
}
