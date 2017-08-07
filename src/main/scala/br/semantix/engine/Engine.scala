package br.semantix.engine

import br.semantix.data.LogLine
import org.apache.spark.rdd.RDD

/**
  * Created by danilo.d.silva on 06/08/2017.
  */
object Engine {

  def getTotalErrors(logs :RDD[LogLine]): Long = {

    logs.filter(_.httpReplyCode == 404).count()
  }
}
