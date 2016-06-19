package org.rwalk.legalchatter

import java.io.{File, IOException}
import javax.sound.sampled.{UnsupportedAudioFileException, AudioSystem, AudioFormat, AudioInputStream}

import edu.cmu.sphinx.api.StreamSpeechRecognizer
import edu.cmu.sphinx.decoder.adaptation.Transform

object LegalChatterUtils {

  def decodeMP3Stream(filename:String):AudioInputStream = {
    // sphinx appears to need .wav streams not MP3s.  Following this example:
    // https://sourceforge.net/p/cmusphinx/discussion/sphinx4/thread/85668745/
    // we can make a decoder
    val audioStream = AudioSystem.getAudioInputStream(new File(filename))
    val baseFormat = audioStream.getFormat()
    val decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
      baseFormat.getSampleRate(),
      16,
      baseFormat.getChannels(),
      baseFormat.getChannels() * 2,
      baseFormat.getSampleRate(),
      false)
    AudioSystem.getAudioInputStream(decodedFormat, audioStream);

  }

  def trainSpeakerModel(filename:String):Transform = {

    // build adaptive speaker profiling
    println("Building spearker model...")
    val recognizer = new StreamSpeechRecognizer(Configurator.sphinxConfig)
    val stats = recognizer.createStats(1)
    recognizer.startRecognition(LegalChatterUtils.decodeMP3Stream(filename))
    var result = recognizer.getResult
    while (result != null) {
      stats.collect(result)
      result = recognizer.getResult()
    }
    recognizer.stopRecognition()
    println("Speaker model built.")

    // now reprocess with the speaker data.
    stats.createTransform()

  }



}


