package com.fdilke.musicxml

import javax.xml.parsers.SAXParserFactory

import scala.io.Source
import scala.xml.{Elem, XML}

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


  def apply(resourceName: String) = {
//    scalaxb.fromXML[Show]( // or [Type]?
      XML.withSAXParser(
        factory.newSAXParser
      ).load(
        Source.fromResource(resourceName).reader()
      )
//    )
  }
}
