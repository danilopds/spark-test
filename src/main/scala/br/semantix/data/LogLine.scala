package br.semantix.data

import org.joda.time.DateTime


/**
  * Created by danilo.d.silva on 06/08/2017.
  */
case class LogLine
(
  clientHost: String,
  hyphenOne: String,
  hyphenTwo: String,
  dateTimestamp: DateTime,
  requestPath: String,
  httpReplyCode: Int,
  bytesSent: Int
)
