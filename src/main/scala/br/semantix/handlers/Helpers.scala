package br.semantix.handlers

import org.slf4j.LoggerFactory

/**
  * Created by danilo.d.silva on 06/08/2017.
  */
object Helpers {

  def getLogger(classType: Class[_ <: Any]): LoggerLocal = {
    val logger = LoggerFactory.getLogger(classType.getCanonicalName)
    new LoggerLocal(Option(logger))
  }

}
