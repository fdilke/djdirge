package com.fdilke.music

import org.scalatest.FreeSpec

class ParseMusicTest extends FreeSpec {

  "ParseMusic2" - {
    "makes sense of an Encoding" in {
      ParseMusic("music/Minuet_in_G_Major_Bach.xml")
    }
  }
}
