package org.rwalk.legalchatter

import java.io.File
import java.io.FileInputStream
import java.io.InputStream

import edu.cmu.sphinx.api.Configuration
import edu.cmu.sphinx.api.SpeechResult
import edu.cmu.sphinx.api.StreamSpeechRecognizer
import org.rwalk.legalchatter.LegalChatterUtils

object TranscriberDemo {

  def main(args:Array[String])  {

    val configuration = new Configuration()

    configuration
      .setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us")
    configuration
      .setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict")
    configuration
      .setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin")

    val recognizer = new StreamSpeechRecognizer(configuration)
    val stream = LegalChatterUtils.decodeMP3Stream(args(0))

    recognizer.startRecognition(stream)
    var result = recognizer.getResult()
    while ( result != null ) {
      println(s"Hypothesis: ${result.getHypothesis()}\n")
      result = recognizer.getResult
    }
    recognizer.stopRecognition()
  }

}