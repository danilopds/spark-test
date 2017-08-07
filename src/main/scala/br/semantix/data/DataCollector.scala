package br.semantix.data

import java.util.regex.Pattern

import br.semantix.handlers.Helpers
import org.apache.spark.SparkContext
import br.semantix.settings.Settings.{logJuly, logAugust}
import org.apache.spark.rdd.RDD

import scala.util.matching.Regex

/**
  * Created by danilo.d.silva on 06/08/2017.
  */
class DataCollector(sc: SparkContext) {


  def getLogs : RDD[LogLine] = {

    val rawLogJuly : RDD[String] = sc.textFile(logJuly)
    val rawLogAug : RDD[String] = sc.textFile(logAugust)
    val rawLog = rawLogJuly ++ rawLogAug

    val logRequests : RDD[LogLine] = rawLog
      .map(line => {
        val pattern1 = "(\\S+)"
        val pattern2 = "\\[([\\w:/]+\\s[+\\-]\\d{4})\\]"
        val pattern3 = "\"(.*?)\""
        val pattern4 = "(\\d{3})"
        val PATTERN = s"^$pattern1 $pattern1 $pattern1 $pattern2 $pattern3 $pattern4 $pattern1"

        val p = Pattern.compile(PATTERN)
        val m = p.matcher(line)

        val output = if (!m.find) {
          println("Rejected Log Line: " + line)
          LogLine("Empty", "-", "-", Helpers.getDateTime("01/Aug/2017:00:00:01 -0400"), "", -1, -1)
        }
        else {
          // NOTE:   HEAD does not have a content size.
          if (m.group(7).equals("-")) {
            LogLine(m.group(1), m.group(2), m.group(3), Helpers.getDateTime(m.group(4)),
              m.group(5), m.group(6).toInt, 0)
          }
          else {
            LogLine(m.group(1), m.group(2), m.group(3), Helpers.getDateTime(m.group(4)),
              m.group(5), m.group(6).toInt, m.group(7).toInt)
          }
        }
        output
      })
      /*.filter(_.clientHost != "Empty")*/
    logRequests
  }

}
