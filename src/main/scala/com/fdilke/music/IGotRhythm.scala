package com.fdilke.music

import com.fdilke.musicxml.EuclideanRhythm
import org.jfugue.player.Player
import org.jfugue.realtime.RealtimePlayer
import org.jfugue.rhythm.Rhythm

object IGotRhythm extends App {
  val euclid32 =
    EuclideanRhythm(9,32)
  val euclid16 =
    EuclideanRhythm(5,16)
  val euclid8_1 =
    EuclideanRhythm(5,8)
  val euclid8_2 =
    EuclideanRhythm(7,8)
  val euclid8_3 =
    EuclideanRhythm(3,8)

  val rhythm =
    new Rhythm().addLayer(
      euclid16.string(0,8,'O')
    ).addLayer(
      euclid32.string(3,4,'X')
    ).addLayer(
      euclid8_1.string(0,16,'S')
    ).addLayer(
      euclid8_2.string(3,16,'^')
    ).addLayer(
      euclid8_3.string(7,16,'*')
    )
  new Player().play(
    rhythm.getPattern.repeat(2)
  )
}

object IGotRhythm2 extends App {
  val euclid6 =
    EuclideanRhythm(2,6)
  val euclid7 =
    EuclideanRhythm(4,7)
  val euclid8 =
    EuclideanRhythm(5,8)
  val euclid3 =
    EuclideanRhythm(2,3)
  val euclid12 =
    EuclideanRhythm(7, 12)

  val rhythm =
    new Rhythm().addLayer(
      euclid6.string(0,28,'S')
    ).addLayer(
      euclid7.string(3,24,'X')
    ).addLayer(
      euclid8.string(0,21,'^')
    ).addLayer(
      euclid3.string(3,56,'O')
//    ).addLayer(
//      euclid12.string(2,14,'*')
    )
  new Player().play(
    rhythm.getPattern.repeat(2)
  )
}
