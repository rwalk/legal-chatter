package org.rwalk.legalchatter

import java.io.File
import java.io.FileInputStream
import java.io.InputStream

import edu.cmu.sphinx.api.Configuration
import edu.cmu.sphinx.api.SpeechResult
import edu.cmu.sphinx.api.StreamSpeechRecognizer
import edu.cmu.sphinx.decoder.adaptation.Transform
import org.rwalk.legalchatter.LegalChatterUtils
import collection.JavaConversions._
import scala.collection.mutable.ListBuffer

object TranscriberDemo {

  def main(args:Array[String])  {

    println("Performing recognition.")
    val recognizer = new StreamSpeechRecognizer(Configurator.sphinxConfig)
    recognizer.startRecognition(LegalChatterUtils.decodeMP3Stream(args(0)))
    var result = recognizer.getResult
    val words = new ListBuffer[String]
    while (result != null) {
      println(s"Hypothesis: ${result.getHypothesis()}\n")

      println("List of recognized words and their times:")
      result.getWords() foreach  println

      println("Best 3 hypothesis:")
      result.getNbest(3) foreach println

      words.append(result.getHypothesis)
      result = recognizer.getResult
    }
    recognizer.stopRecognition()
    println(s"TRANSCRIPTION: \n ${words.mkString(" ")}")

  }

}