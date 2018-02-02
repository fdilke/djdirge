package com.fdilke.musicxml

import org.scalatest.FreeSpec
import org.scalatest.Matchers._

import scala.reflect.ClassTag
import scalaxb.XMLFormat

class ParseMusicTest extends FreeSpec {

  private def parses[T : XMLFormat : ClassTag](fragmentName: String) =
    ParseMusic[T](s"fragments/$fragmentName.xml") shouldBe a[T]

  "ParseMusic" - {
    "makes sense of an Encoding" in {
      parses[Encoding]("Encoding")
    }

    "makes sense of an Identification" in {
      parses[Identification]("Identification")
    }

    "makes sense of a Defaults" in {
      parses[Defaults]("Defaults")
    }

    "makes sense of a Credit" in {
      parses[Credit]("Credit")
    }

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
