package br.semantix.app

import br.semantix.data.DataCollector
import br.semantix.engine.Engine
import br.semantix.handlers.{Helpers, LoggerLocal, SparkHandler}
import br.semantix.settings.Settings
import br.semantix.settings.Settings.appName
import org.apache.spark.SparkContext

/**
  * Created by danilo.d.silva on 05/08/2017.
  */
object AppTest {
  val logger: LoggerLocal = Helpers.getLogger(this.getClass)
  val sc: SparkContext = SparkHandler.getSparkContext(appName)
  val dataCollector: DataCollector = new DataCollector(sc)

  def main(args: Array[String]): Unit = {
    // required for winutils
    System.setProperty("hadoop.home.dir", Settings.winUtils)

    // getting requests
    val logRequests = dataCollector.getLogs

    //question 2
    val errors = Engine.getTotalErrors(logRequests)
    println("Number of 404 errors: "+errors)

    /*logRequests.foreach(println)*/

  }

}
