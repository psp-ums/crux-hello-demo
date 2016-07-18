package ums.cruxscalademo.helloservice.internal.formatting

import java.util.Date

import cellvision.crux.http.HttpResponseCodes
import cellvision.crux.soh.{Id, SelftestResult, Selftestable}
import ums.cruxscalademo.helloservice.Lang.Lang
import ums.cruxscalademo.helloservice.internal.config.HelloServiceConfig
import ums.cruxscalademo.helloservice.{HelloMessage, UnknownLanguageException}

import scala.collection.concurrent.TrieMap

/**
  * Class for formatting hello messages
  *
  * @author psp
  * @since 2016.07.15
  */
class Formatter(config: HelloServiceConfig)  {

  val unresolved = new TrieMap[Lang, Null]
  val invocationStats = new TrieMap[Lang, Date]

  def format(lang: Lang): HelloMessage = {
    val message = config.greetings.find(_.language == lang) match {
      case Some(greeting) =>
        invocationStats.put(lang, new Date)
        unresolved -= lang
        s"${greeting.hello}${config.separator}${greeting.signature}"
      case None =>
        unresolved += lang -> null
        throw new UnknownLanguageException(s"Missing configuration for language: $lang")
    }
    HelloMessage(message)
  }

}
