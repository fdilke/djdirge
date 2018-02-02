package com.fdilke.musicxml

import org.scalatest.FreeSpec

class ParseMusic2Test extends FreeSpec {

  "ParseMusic2" - {
    "makes sense of an Encoding" in {
      ParseMusic2("music/Telemann.xml")
    }
  }
}
