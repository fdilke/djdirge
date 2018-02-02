package com.fdilke.musicxml

import org.scalatest.FreeSpec
import org.scalatest.Matchers._

import scala.reflect.ClassTag
import scalaxb.XMLFormat

class ParseMusicTest extends FreeSpec {

  private def parsesFile[T : XMLFormat : ClassTag](path: String) =
    ParseMusic[T](path) shouldBe a[T]

  private def parsesFragment[T : XMLFormat : ClassTag](fragmentName: String) =
    parsesFile[T](s"fragments/$fragmentName.xml")

  "ParseMusic" - {
    "makes sense of an Encoding" in {
      parsesFragment[Encoding]("Encoding")
    }

    "makes sense of an Encoding (2)" in {
      parsesFragment[Encoding]("Encoding2")
    }

    "makes sense of an Identification" in {
      parsesFragment[Identification]("Identification")
    }

    "makes sense of an Identification (2)" in {
      parsesFragment[Identification]("Identification2")
    }

    "makes sense of a Defaults" in {
      parsesFragment[Defaults]("Defaults")
    }

    "makes sense of a Defaults (2)" in {
      parsesFragment[Defaults]("Defaults2")
    }

    "makes sense of a Credit" in {
      parsesFragment[Credit]("Credit")
    }

//    "makes sense of a Scoreu45part" in {
//      parsesFragment[Partu45list]("Scoreu45part")
//    }
//    "makes sense of a Scoreu45part (2)" in {
//      parsesFragment[Scoreu45part]("Scoreu45part2")
//    }

//    "makes sense of a Partu45list" in {
//      parses[Partu45list]("Partu45list")
//    }

//    "makes sense of a file" in {
//      parsesFile[Scoreu45partwise](s"music/Telemann.xml")
//    }

//    "makes sense of a file" in {
//      try {
//        val parsed = ParseMusic("music/lg-22484616.xml")
//
//        println("parsed!! type " + parsed.getClass.getSimpleName)
//      } catch {
//        case ex: Exception =>
//          println("ex is a " + ex.getClass.getSimpleName)
//          ex.printStackTrace()
//      }
//    }
  }
}
