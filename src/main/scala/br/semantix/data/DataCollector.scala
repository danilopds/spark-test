package br.semantix.data

import java.util.regex.Pattern

import org.apache.spark.SparkContext
import br.semantix.settings.Settings.logJuly
import org.apache.spark.rdd.RDD

import scala.util.matching.Regex

/**
  * Created by danilo.d.silva on 06/08/2017.
  */
class DataCollector(sc: SparkContext) {


  def getLogs: RDD[LogLine] = {

    val rawLog: RDD[String] = sc.textFile(logJuly)
    val x: RDD[LogLine] = rawLog
      .map(line => {
        val request = "\"(.*?)\""
        val dateTime = "\\[([\\w:/]+\\s[+\\-]\\d{4})\\]"
        val PATTERN = s"^(\\S+) (\\S+) (\\S+) $dateTime $request (\\d{3}) (\\S+)"
        val p = Pattern.compile(PATTERN)
        val res = p.matcher(line)

        val output = if (!res.find) {
          println("Rejected Log Line: " + line)
          LogLine("Empty", "-", "-", "", "", -1, -1)
        }
        else {
          val m = res
          // NOTE:   HEAD does not have a content size.
          if (m.group(7).equals("-")) {
            LogLine(m.group(1), m.group(2), m.group(3), m.group(4),
              m.group(5), m.group(6).toInt, 0)
          }
          else {
            LogLine(m.group(1), m.group(2), m.group(3), m.group(4),
              m.group(5), m.group(6).toInt, m.group(7).toInt)
          }
        }
        output
      })
      .filter(_.clientHost != "Empty")

    x
  }

}
