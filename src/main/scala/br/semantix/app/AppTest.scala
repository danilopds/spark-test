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
    /*logRequests.foreach(println)*/

    //question 1
    val hosts = Engine.getUniqueHosts(logRequests)
    println("Number of unique hosts: "+hosts)

    //question 2
    val errors = Engine.getTotalErrors(logRequests)
    println("Number of 404 errors: "+errors)

    //question 3
    val brokenURLs = Engine.getTopBrokenURLs(logRequests)
    brokenURLs.foreach(println)

    //question 4
    val brokenURLsByDay = Engine.getTotalErrorsByDay(logRequests)
    brokenURLsByDay.foreach(println)

    //question 5
    val bytesReturned = Engine.getTotalBytes(logRequests)
    println("Total of returned bytes: "+bytesReturned)



  }

}
