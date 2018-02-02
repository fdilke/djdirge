package com.fdilke.musicxml

import javax.sound.midi.MidiSystem
import javax.xml.parsers.SAXParserFactory

import nu.xom.Document
import org.jfugue.integration.{MusicXmlParser, MusicXmlParserListener}
import org.jfugue.parser.ParserListener
import org.jfugue.pattern.Pattern
import org.jfugue.player.Player
import org.jfugue.theory.ChordProgression
import org.staccato.StaccatoParserListener

import scala.io.Source
import scala.xml.XML
import scalaxb.XMLFormat

object ParseMusic2 {
  def apply(path: String) {
    val parser = new MusicXmlParser
//    val listener = new MusicXmlParserListener
//    parser.addParserListener(listener)
//    val document: Document =
//      listener.getMusicXMLDoc

    val listener2 = new StaccatoParserListener
    parser.addParserListener(listener2)
    parser.parse(
      Source.fromResource(path).getLines() map {
        _ replaceAll("\"http://www.musicxml.org/dtds/partwise.dtd\"",
          "\"./musicxml-3.1/schema/partwise.dtd\"")
      } mkString("\n")
    )

    val staccatoPattern = listener2.getPattern
    println(staccatoPattern)

    val player = new Player()
    player.play(staccatoPattern)
//    listener.
  }
}

import org.jfugue.player.Player
import org.jfugue.rhythm.Rhythm

object TryThis extends App {
  new Player().play(
    new ChordProgression(
      "I IV vi V"
    ).eachChordAs(
      "$!i $!i Ri $!i"
    ),
    new Rhythm().addLayer(
      "..X...X...X...XO"
    )
  )
}

object IntroToRhythms extends App {
    val rhythm =
      new Rhythm().addLayer(
        "O..oO...O..oOO.."
      ).addLayer(
        "..S...S...S...S."
      ).addLayer(
        "````````````````"
      ).addLayer(
        "...............+"
      )
    new Player().play(
      rhythm.getPattern.repeat(2)
    )
}