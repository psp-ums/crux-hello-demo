package ums.cruxscalademo.helloservice.internal.config

import java.util.NoSuchElementException

import cellvision.crux.config.annotation._
import cellvision.crux.service.CruxServiceRef
import ums.cruxscalademo.helloservice.Lang._
import ums.cruxscalademo.helloservice.{HelloService, Lang, UnknownLanguageException}

import scala.annotation.meta.field

/**
 * A configuration class that will be automatically registered by the configuration framework due to the @Config annotation.
 * Need to be in the bundle context. @Rank and @Description are used by CruxConsole UI.
 *
 * @author psp
 * @since 2016.07.14
 */
@Config(domain = "Hello", name = "HelloService")
@Description("Config bean for HelloService")
class HelloServiceConfig(
                          @(Rank@field)(1)
                          @(Description@field)("The separator between the message and the signature")
                          var separator: String,
                          @(Rank@field)(2)
                          @(Description@field)("A list of greetings for various countries. Each country should occur only once")
                          var greetings: Iterable[Greeting] = Iterable(Greeting(NO, "Hallo Verden", "Fra Crux"), Greeting(GB, "Hello World", "From Crux"), Greeting(DE, "Hallo Welt", "Von Crux"), Greeting(IN, "नमस्ते दुनिया", ""))
                        ) {
  def this() = this(" ")

  @Description("Optional test method to test the service interface from CruxConsole")
  @ParamNames(Array("language"))
  @ParamDescriptions(Array("The language name being an ISO3166-2 code, i.e two capital letters"))
  def testPrintHelloWorld(lang: String) {
    CruxServiceRef[HelloService].printHelloWorld(toLang(lang))
  }

  @Description("Optional test method to test the service interface from CruxConsole")
  @ParamNames(Array("language"))
  @ParamDescriptions(Array("The language name being an ISO3166-2 code, i.e two capital letters"))
  def testGetHelloWorld(lang: String): String = {
    CruxServiceRef[HelloService].getHelloWorldGreeting(toLang(lang)).toString
  }

  private def toLang(s: String): Lang = {
    try {
      Lang.withName(s)
    } catch {
      case e: NoSuchElementException => throw new UnknownLanguageException(s"No enumeration exists for language '$s'")
    }
  }

}
