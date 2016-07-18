package ums.cruxscalademo.helloservice.internal.config

import cellvision.crux.config.annotation.Rank
import ums.cruxscalademo.helloservice.Lang


import scala.annotation.meta.field

/**
  * This class represents a row in a configuration list
  *
  * @author psp
  * @since 2016.07.14
  */
class Greeting(@(Rank@field)(1) var language: Lang.Value, @(Rank@field)(2) var hello: String = "", @(Rank@field)(3) var signature: String = "") {
  def this() = this(Lang.NO)
}

object Greeting {
  def apply(l: Lang.Value, h: String, s: String): Greeting = new Greeting(l, h, s)
}
