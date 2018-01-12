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
//    scalaxb.fromXML[Encoding](
//      <encoding>
//        <software>MuseScore 2.0.2</software>
//        <encoding-date>2015-12-04</encoding-date>
//        <supports element="accidental" type="yes"/>
//        <supports element="beam" type="yes"/>
//        <supports element="print" attribute="new-page" type="yes" value="yes"/>
//        <supports element="print" attribute="new-system" type="yes" value="yes"/>
//        <supports element="stem" type="yes"/>
//      </encoding>
//    )

//    scalaxb.fromXML[Identification](
//      <identification>
//        <rights>public domain</rights>
//        <encoding>
//          <software>MuseScore 2.0.2</software>
//          <encoding-date>2015-12-04</encoding-date>
//          <supports element="accidental" type="yes"/>
//          <supports element="beam" type="yes"/>
//          <supports element="print" attribute="new-page" type="yes" value="yes"/>
//          <supports element="print" attribute="new-system" type="yes" value="yes"/>
//          <supports element="stem" type="yes"/>
//        </encoding>
//        <source>http://musescore.com/score/1482661</source>
//      </identification>
//    )

//    scalaxb.fromXML[Defaults](
//      <defaults>
//        <scaling>
//          <millimeters>7.05556</millimeters>
//          <tenths>40</tenths>
//        </scaling>
//        <page-layout>
//          <page-height>1683.78</page-height>
//          <page-width>1190.55</page-width>
//          <page-margins type="even">
//            <left-margin>56.6929</left-margin>
//            <right-margin>56.6929</right-margin>
//            <top-margin>56.6929</top-margin>
//            <bottom-margin>113.386</bottom-margin>
//          </page-margins>
//          <page-margins type="odd">
//            <left-margin>56.6929</left-margin>
//            <right-margin>56.6929</right-margin>
//            <top-margin>56.6929</top-margin>
//            <bottom-margin>113.386</bottom-margin>
//          </page-margins>
//        </page-layout>
//        <word-font font-family="FreeSerif" font-size="10"/>
//        <lyric-font font-family="FreeSerif" font-size="11"/>
//      </defaults>
//    )

//    scalaxb.fromXML[Credit](
//      <credit page="1">
//        <credit-words default-x="595.275" default-y="1627.09" justify="center" valign="top" font-family="Times New Roman" font-size="24">Für Elise</credit-words>
//      </credit>
//    )

    scalaxb.fromXML[Partu45list](
      <part-list>
        <score-part id="P1">
          <part-name></part-name>
          <score-instrument id="P1-I1">
            <instrument-name></instrument-name>
          </score-instrument>
          <midi-device id="P1-I1" port="1"></midi-device>
          <midi-instrument id="P1-I1">
            <midi-channel>1</midi-channel>
            <midi-program>1</midi-program>
            <volume>78.7402</volume>
            <pan>0</pan>
          </midi-instrument>
        </score-part>
      </part-list>
    )

//    scalaxb.fromXML[Scoreu45partwise]( // or [Type], [Show]?
//      XML.withSAXParser(
//        factory.newSAXParser
//      ).load(
//        Source.fromResource(resourceName).reader()
//      )
//    )
  }
}
