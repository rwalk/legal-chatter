package org.rwalk.legalchatter

import java.io.{File, IOException}
import javax.sound.sampled.{UnsupportedAudioFileException, AudioSystem, AudioFormat, AudioInputStream}

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

}


