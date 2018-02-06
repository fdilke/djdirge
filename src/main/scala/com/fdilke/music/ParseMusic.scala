package com.fdilke.music

import java.io._
import javax.sound.midi.{MidiSystem, Synthesizer}

import org.jfugue.integration.MusicXmlParser
import org.jfugue.pattern.Pattern
import org.jfugue.player.{Player, SequencerManager, SynthesizerManager}
import org.jfugue.theory.{ChordProgression, Note}
import org.staccato.StaccatoParserListener
import com.fdilke.music.util.Enrichments._
import org.jfugue.realtime.RealtimePlayer
import org.jfugue.theory.Note.OCTAVE

import scala.io.Source

object ParseMusic {
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

object CrabCanon extends App {
    // One voice of Bach’s Crab Canon
    val canon = new Pattern("D5h E5h A5h Bb5h C#5h Rq A5q "+
    "A5q Ab5h G5q G5q F#5h F5q F5q E5q Eb5q D5q "+
  "C#5q A3q D5q G5q F5h E5h D5h F5h A5i G5i A5i "+
    "D6i A5i F5i E5i F5i G5i A5i B5i C#6i D6i F5i "+
    "G5i A5i Bb5i E5i F5i G5i A5i G5i F5i E5i F5i "+
    "G5i A5i Bb5i C6i Bb5i A5i G5i A5i Bb5i C6i D6i "+
    "Eb6i C6i Bb5i A5i B5i C#6i D6i E6i F6i D6i "+
    "C#6i B5i C#6i D6i E6i F6i G6i E6i A5i E6i D6i "+
    "E6i F6i G6i F6i E6i D6i C#6i D6q A5q F5q D5q")
  // Create a new pattern that is the reverse of the first pattern
  val reverseCanon = canon.reverse
  // Lower the octaves of the reversed pattern
  val octaveCanon = reverseCanon.changeNotes( _ - OCTAVE )
  // Combine the two patterns
  // instruments to try: Crystal, Fiddle, Reed_Organ, Flute, Piano, Voice,
  // Whistle, English_Horn, Banjo, Warm, Goblins, Atmosphere, Soprano_Sax
  // Echoes, Skakuhachi, Rain, Square, Sawtooth, Oboe, Clarinet, Bagpipe
  // Glockenspiel, Church_Organ
  val pattern = new Pattern("T[VIVACE]")
  pattern.add("V0 I[Bagpipe] " + canon.getPattern.toString)
  pattern.add("V1 I[Glockenspiel] " + octaveCanon.getPattern.toString)
  // Play Bach’s Crab Canon
  val player = new Player()
  player.play(pattern)
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
    new RealtimePlayer().play(
      rhythm.getPattern.repeat(2)
    )
}

object Hack extends App {

  val synth = MidiSystem.getSynthesizer()
  val soundBankStream = new BufferedInputStream(
    new FileInputStream(
      new File(
//        "/Users/Felix/Downloads/soundbank-deluxe.gm"
//        "/Users/Felix/Downloads/Arianna'sFluidR3Mono_GM2.sf2"
//        "/Users/Felix/Downloads/fluid-soundfont-3.1/FluidR3_GM.sf2"
        "/Users/Felix/Downloads/FluidR3 GM.sf2"
      )
    )
  )
//  val hh =  MidiSystem.getSoundbank(
//    soundBankStream
//  )
//    getClass().getClassLoader().getResourceAsStream(
//      "/Users/Felix/Downloads/soundbank-deluxe.gm"
//    )
//  )
  synth.open()
  synth.loadAllInstruments(MidiSystem.getSoundbank(soundBankStream))
//  synth.close()
//  val soundbank = MidiSystem.getSynthesizer.getDefaultSoundbank
//  println("soundbank = " + soundbank.getDescription + " ::== " +
//    soundbank.getName)
  // was: soundbank = Emergency generated soundbank ::== Emergency GM sound set
  SynthesizerManager.getInstance().setSynthesizer(synth)
  SequencerManager.getInstance().connectSequencerToSynthesizer()
  CrabCanon.main(null)
}

object TryConversion extends App {
  MidiToWavConverter.midi2wav(
    new FileInputStream(
      new File("/Users/Felix/Downloads/level1.midi")
    ),
    new FileOutputStream(
      new File("/Users/Felix/Downloads/experimental.wav")
    )
  )
}