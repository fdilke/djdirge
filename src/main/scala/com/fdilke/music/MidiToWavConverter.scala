package com.fdilke.music

import java.io._
import java.util.concurrent.atomic.{AtomicInteger, AtomicLong}
import javax.sound.midi.InvalidMidiDataException
import javax.sound.midi.MetaMessage
import javax.sound.midi.MidiDevice
import javax.sound.midi.MidiDevice.Info
import javax.sound.midi.MidiEvent
import javax.sound.midi.MidiMessage
import javax.sound.midi.MidiSystem
import javax.sound.midi.MidiUnavailableException
import javax.sound.midi.Receiver
import javax.sound.midi.Sequence
import javax.sound.midi.Synthesizer
import javax.sound.midi.Track
import javax.sound.sampled.AudioFileFormat
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem

import scala.collection.JavaConverters._
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import com.sun.media.sound.AudioSynthesizer
import com.sun.swing.internal.plaf.synth.resources.synth
import org.jfugue.midi.MidiFileManager
import org.jfugue.pattern.{Pattern, PatternProducer}
import org.jfugue.player.SynthesizerManager

import scala.language.postfixOps;

object MidiToWavConverter {

  def savePatternToWav(
    patternProducer: PatternProducer,
    path: String
  ) {
    val byteOut = new ByteArrayOutputStream
    MidiFileManager.savePatternToMidi(
      patternProducer,
      byteOut
    )
    midi2wav(
      new ByteArrayInputStream(byteOut.toByteArray),
      new FileOutputStream(
        new File(path)
      )
    )
  }

  def midi2wav(in: InputStream, out: OutputStream) {
    val sequence = MidiSystem.getSequence(in)
    render(sequence, out)
  }

  private def render(sequence : Sequence , out: OutputStream) {
      val synth = findAudioSynthesizer()

      var stream = synth.openStream(null, null)

      val total = send(sequence, synth.getReceiver());

      // Calculate how long the WAVE file needs to be.
      val len = (stream.getFormat().getFrameRate() * (total + 4)).toLong
      stream = new AudioInputStream(stream, stream.getFormat(), len)

      AudioSystem.write(stream, AudioFileFormat.Type.WAVE, out)

      synth.close()
  }

  private def findAudioSynthesizer() =
    MidiSystem.getSynthesizer match {
      case dev: AudioSynthesizer => dev
      case _ =>
        MidiSystem.getMidiDeviceInfo.map { info =>
          MidiSystem.getMidiDevice(info)
        } collect {
          case dev: AudioSynthesizer => dev
        } head
    }

  private def send(seq: Sequence, recv: Receiver): Double = {
    val divtype = seq.getDivisionType()
    assert (seq.getDivisionType() == Sequence.PPQ)
    val tracks = seq.getTracks
    val trackspos = Seq.fill(tracks.length)(new AtomicInteger(0))
    val mpq = new AtomicInteger(500000)
    val seqres = seq.getResolution()
    val lasttick = new AtomicLong(0)
    val curtime = new AtomicLong(0)
    var keepRunning = true
    while (keepRunning) {
      var selevent: MidiEvent = null
      var seltrack = -1
      for (i <- 0 until tracks.length) {
        val trackpos = trackspos(i).get()
        val track = tracks(i)
        if (trackpos < track.size()) {
          val event: MidiEvent = track.get(trackpos)
          if (selevent == null || event.getTick() < selevent.getTick()) {
            selevent = event
            seltrack = i
          }
        }
      }
      if (seltrack == -1)
        keepRunning = false
      else {
        trackspos(seltrack).incrementAndGet()
        val tick = selevent.getTick()
        if (divtype == Sequence.PPQ)
          curtime.addAndGet((tick - lasttick.get) * mpq.get / seqres)
        else
          curtime.set(((tick * 1000000.0 * divtype) / seqres).toLong)
        lasttick.set(tick)
        val msg: MidiMessage = selevent.getMessage()
        if (msg.isInstanceOf[MetaMessage]) {
          val theMsg = msg.asInstanceOf[MetaMessage]
          if (divtype == Sequence.PPQ) {
            if (theMsg.getType() == 0x51) {
              val data = theMsg.getData()
              mpq.set(((data(0) & 0xff) << 16) |
                ((data(1) & 0xff) << 8) |
                (data(2) & 0xff)
              )
            }
          }
        } else {
          if (recv != null)
            recv.send(msg, curtime.get)
        }
      }
    }

    curtime.get / 1000000.0
  }
}
