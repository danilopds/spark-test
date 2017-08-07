package br.semantix.settings

/**
  * Created by danilo.d.silva on 05/08/2017.
  */
import com.typesafe.config.ConfigFactory

object Settings {
  private val config = ConfigFactory.load()

  private val sparkSettings = config.getConfig("spark_settings")
  private val hadoopSettings = config.getConfig("hadoop_settings")
  private val logSettings = config.getConfig("log_settings")

  lazy val appName: String = sparkSettings.getString("appName")
  lazy val winUtils: String = hadoopSettings.getString("winUtils")
  lazy val logJuly: String = logSettings.getString("logJuly")
  lazy val logAugust: String = logSettings.getString("logAugust")

}
