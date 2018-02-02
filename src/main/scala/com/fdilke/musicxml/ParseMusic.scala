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
//      <part-list>
//        <score-part id="P1">
//          <part-name></part-name>
//          <score-instrument id="P1-I1">
//            <instrument-name></instrument-name>
//          </score-instrument>
//          <midi-device id="P1-I1" port="1"></midi-device>
//          <midi-instrument id="P1-I1">
//            <midi-channel>1</midi-channel>
//            <midi-program>1</midi-program>
//            <volume>78.7402</volume>
//            <pan>0</pan>
//          </midi-instrument>
//        </score-part>
//      </part-list>
//    )

//    scalaxb.fromXML[Scoreu45partwise]( // or [Type], [Show]?
//      XML.withSAXParser(
//        factory.newSAXParser
//      ).load(
//        Source.fromResource(resourceName).reader()
//      )
//    )
}
