package ums.cruxscalademo.helloweb.config

import cellvision.crux.config.annotation._
import cellvision.crux.http.HttpResponseCodes
import cellvision.crux.service.CruxServiceRef

import scala.annotation.meta.{field, getter}

/**
  * A configuration class that will be automatically registered by the configuration framework due to the @Config annotation. Need to be in the bundle context.
  *
  * @author psp
  * @since 2016.07.14
  */
@Config(domain = "Hello", name = "HelloApi")
@Description("Config bean for HelloWeb")
class HelloWebConfig(
                     @(Rank@field)(1)
                     @(ParamNames@field)(Array("HTTP code"))
                     @(ParamDescriptions@field)(Array("The HTTP result code to return"))
                     var contentType: String) {

  def this() = this("text/html;charset=UTF-8")
}
