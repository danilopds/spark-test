package br.semantix.settings

/**
  * Created by danilo.d.silva on 05/08/2017.
  */
import com.typesafe.config.ConfigFactory

object Settings {
  private val config = ConfigFactory.load()

  //Plano Crescimento
  //lazy val appName: String = weblogGen.getString("appName")

}
