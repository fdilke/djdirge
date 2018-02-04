package com.fdilke.music.util

import org.jfugue.pattern.{Pattern, Token}
import org.jfugue.theory.Note
import org.scalatest.FreeSpec
import org.scalatest.Matchers._
import Enrichments._
import org.jfugue.pattern.Token.TokenType

import scala.collection.JavaConverters._

class EnrichmentsTest extends FreeSpec {

  "RichToken" - {
    "imposes sensible semantics of equality on tokens" in {
      val tokenA = new Token("A", TokenType.INSTRUMENT)

      (tokenA: RichToken) shouldBe
        new Token("A", TokenType.INSTRUMENT)
      (tokenA: RichToken) should not be
        new Token("a", TokenType.INSTRUMENT)
      (tokenA: RichToken) should not be
        new Token("B", TokenType.INSTRUMENT)
      (tokenA: RichToken) should not be
        new Token("A", TokenType.ATOM)

      (tokenA: RichToken) shouldBe (
        new Token("A", TokenType.INSTRUMENT): RichToken
        )
      (tokenA: RichToken) should not be (
        new Token("a", TokenType.INSTRUMENT): RichToken
        )
      (tokenA: RichToken) should not be (
        new Token("B", TokenType.INSTRUMENT): RichToken
        )
      (tokenA: RichToken) should not be (
        new Token("A", TokenType.ATOM): RichToken
        )

      (tokenA: RichToken) should not be 0
    }
  }

  "RichNote" - {
    "can add to a note, changing its value" in {
      val note = new Note("A5w")

      (note + Note.OCTAVE).toString shouldBe
        "A6w"
    }

    "can subtract from a note, changing its value" in {
      val note = new Note("A5w")

      (note - Note.OCTAVE).toString shouldBe
        "A4w"
    }
  }

  "RichPattern" - {
    "can invert a pattern" in {
      val pattern = new Pattern("A5 I[FLUTE] B5 C5")

      pattern.reverse.toString shouldBe
        "C5 B5 I[FLUTE] A5"
    }

    "can apply note functions to a pattern" in {
      val pattern = new Pattern("A5 I[FLUTE] B5w C5h")

      pattern.changeNotes {
        _ - Note.OCTAVE
      }.toString shouldBe
        "A4q I[FLUTE] B4w C4h"
    }
  }
}
