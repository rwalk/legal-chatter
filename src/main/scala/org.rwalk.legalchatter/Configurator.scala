package org.rwalk.legalchatter

import edu.cmu.sphinx.api.Configuration

object Configurator {

  val sphinxConfig = new Configuration()

  println("Loading models...")
  sphinxConfig
    .setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us")
  sphinxConfig
    .setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict")
  sphinxConfig
    .setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin")

}
