package br.semantix.handlers

import org.joda.time.DateTime
import org.joda.time.format.{DateTimeFormat, DateTimeFormatter}
import org.slf4j.LoggerFactory

/**
  * Created by danilo.d.silva on 06/08/2017.
  */
object Helpers {
  val dateFormatter: DateTimeFormatter = DateTimeFormat.forPattern("dd/MMM/yyyy")
  val dateTimeFormatter: DateTimeFormatter = DateTimeFormat.forPattern("dd/MMM/yyyy:HH:mm:ss Z")

  def getLogger(classType: Class[_ <: Any]): LoggerLocal = {
    val logger = LoggerFactory.getLogger(classType.getCanonicalName)
    new LoggerLocal(Option(logger))
  }

  def getDateTime(dateStr: String):DateTime = {
    if (dateStr.length > 10)
      dateTimeFormatter.parseDateTime(dateStr)
    else
      dateFormatter.parseDateTime(dateStr)
  }

}
