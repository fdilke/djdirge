package com.fdilke.musicxml

import org.jfugue.pattern.Pattern
import org.jfugue.theory.Note
import scala.collection.JavaConverters._

object MusicUtils {
  def reverse(melody1: Pattern) = {
    val melody2 = new Pattern
    for (token <- melody1.getTokens.asScala) {
      val note = new Note(token.toString)
      melody2.prepend(note)
    }
    melody2
  }

  def lowerOctave(melody1: Pattern) = {
    val melody2 = new Pattern
    for (token <- melody1.getTokens.asScala) {
      val note = new Note(token.toString)
      note.changeValue(-Note.OCTAVE)
      melody2.add(note)
    }
    melody2
  }
}
