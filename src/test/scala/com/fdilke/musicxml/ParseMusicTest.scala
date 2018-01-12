package com.fdilke.musicxml

import org.scalatest.FreeSpec

class ParseMusicTest extends FreeSpec {
  "ParseMusic" - {
    "makes sense of a file" in {
      try {
        val parsed = ParseMusic("music/lg-22484616.xml")

        println("parsed!! type " + parsed.getClass.getSimpleName)
      } catch {
        case ex: Exception =>
          println("ex is a " + ex.getClass.getSimpleName)
          ex.printStackTrace()
      }
    }
  }
}
