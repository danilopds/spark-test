package br.semantix.engine

import br.semantix.data.LogLine
import org.apache.spark.rdd.RDD

/**
  * Created by danilo.d.silva on 06/08/2017.
  */
object Engine {

  def getUniqueHosts(logs :RDD[LogLine]): Long = {
    logs
      .map(_.clientHost)
      .distinct()
      .count()
  }

  def getTotalErrors(logs :RDD[LogLine]): Long = {
    logs
      .filter(_.httpReplyCode == 404)
      .count()
  }

  def getTopBrokenURLs(logs :RDD[LogLine]) : Array[(String, Int)] = {
    logs
      .filter(_.httpReplyCode == 404)
      .map(l => (l.requestPath, 1))
      .reduceByKey(_ + _)
      .sortBy(_._2, ascending = false)
      .take(5)
  }

  def getTotalErrorsByDay(logs :RDD[LogLine]) : Array[(String, Int)] = {
    logs
      .filter(_.httpReplyCode == 404)
      .map(l => (l.dateTimestamp.toLocalDate.toString(), 1))
      .reduceByKey(_ + _)
      .collect()
  }

  def getTotalBytes(logs :RDD[LogLine]) : Double = {
    logs
      .map(_.bytesSent)
      .sum()
  }
}
