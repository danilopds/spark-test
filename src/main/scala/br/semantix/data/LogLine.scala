package br.semantix.data

/**
  * Created by danilo.d.silva on 06/08/2017.
  */
case class LogLine
(
  clientHost: String,
  hyphenOne: String,
  hyphenTwo: String,
  dateTimestamp: String,
  requestPath: String,
  httpReplyCode: Int,
  bytesSent: Int
)
