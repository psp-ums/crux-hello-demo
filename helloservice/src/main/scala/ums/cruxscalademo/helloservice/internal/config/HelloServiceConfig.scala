package ums.cruxscalademo.helloservice.internal.config

import java.util.NoSuchElementException

import cellvision.crux.config.annotation._
import cellvision.crux.service.CruxServiceRef
import ums.cruxscalademo.helloservice.{UnknownLanguageException, HelloService, Lang}
import ums.cruxscalademo.helloservice.Lang._


import scala.annotation.meta.{field, getter}

/**
  * A configuration class that will be automatically registered by the configuration framework due to the @Config annotation. Need to be in the bundle context.
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
                         @(ParamNames@field)(Array("Language", "Hello text", "Signature"))
                         @(ParamDescriptions@field)(Array("The language of the message", "The actual hello greeting", "A signature indicating who sent the greeting"))
                         var greetings: Iterable[Greeting] = Iterable(Greeting(NO, "Hallo Verden", "Fra Crux"), Greeting(GB, "Hello World", "From Crux"), Greeting(DE, "Hallo Welt", "Von Crux"))
                        ) {
  def this() = this(" ")

  /** Optional test method to test the service interface from CruxConsole */
  def testPrintHelloWorld(lang: String) {
    CruxServiceRef[HelloService].printHelloWorld(toLang(lang))
  }

  /** Optional test method to test the service interface from CruxConsole */
  def testGetHelloWorld(lang: String): String =  {
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
