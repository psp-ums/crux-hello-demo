package ums.cruxscalademo.helloservice.internal.config

import cellvision.crux.config.annotation.{Description, Rank}
import ums.cruxscalademo.helloservice.Lang

import scala.annotation.meta.field

/**
  * This class represents a row in a configuration list. @Rank and @Description annotations are used by CruxConsole UI.
  *
  * @author psp
  * @since 2016.07.14
  */
class Greeting(
                @(Rank@field)(1)
                @(Description@field)("The language represented by a ISO3166")
                var language: Lang.Value,

                @(Rank@field)(2)
                @(Description@field)("The actual hello world greeting")
                var hello: String = "",

                @(Rank@field)(3)
                @(Description@field)("A signature added to the greeting")
                var signature: String = "") {
  def this() = this(Lang.NO)
}

object Greeting {
  def apply(l: Lang.Value, h: String, s: String): Greeting = new Greeting(l, h, s)
}
