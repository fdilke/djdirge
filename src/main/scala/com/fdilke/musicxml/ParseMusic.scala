package com.fdilke.musicxml

import javax.xml.parsers.SAXParserFactory

import scala.io.Source
import scala.xml.{Elem, XML}
import scalaxb.XMLFormat

object ParseMusic {

  val factory = SAXParserFactory.newInstance

  // disable DTD validation
  factory.setValidating(false)
//  factory.setFeature("http://apache.org/xml/features/nonvalidat‌​ing/load-external-dt‌​d", false)
  factory.setFeature("http://xml.org/sax/features/validation", false)
  factory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false)
  factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false)
  factory.setFeature("http://xml.org/sax/features/external-general-entities", false)
  factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false)

  def apply[T : XMLFormat](resourceName: String) =
        scalaxb.fromXML[T](
          XML.withSAXParser(
            factory.newSAXParser
          ).load(
            Source.fromResource(resourceName).reader()
          )
        )

//    scalaxb.fromXML[Partu45list](
//    )

//    scalaxb.fromXML[Scoreu45partwise]( // or [Type], [Show]?
//      XML.withSAXParser(
//        factory.newSAXParser
//      ).load(
//        Source.fromResource(resourceName).reader()
//      )
//    )
}
