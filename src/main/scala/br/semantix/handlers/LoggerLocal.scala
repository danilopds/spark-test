package br.semantix.handlers

import org.slf4j.Logger

/**
  * Created by danilo.d.silva on 06/08/2017.
  */
class LoggerLocal (logger:Option[Logger]) extends Serializable{
  def info(message: String) : Unit= {
    if (logger.isDefined)
      logger.get.info(message)
    else println("Logger not Found: " + message)
  }

  def debug(message: String) : Unit= {
    if (logger.isDefined)
      logger.get.debug(message)
    else println("Logger not Found: " + message)
  }

  def error(message: String) : Unit= {
    if (logger.isDefined)
      logger.get.error(message)
    else println("Logger not Found: " + message)
  }
}

