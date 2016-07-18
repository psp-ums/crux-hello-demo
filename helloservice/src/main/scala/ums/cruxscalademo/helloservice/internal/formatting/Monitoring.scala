package ums.cruxscalademo.helloservice.internal.formatting

import java.util.Date

import cellvision.crux.monitoring.{MonitoredAttribute, MonitoringBean}
import ums.cruxscalademo.helloservice.Lang.Lang

/**
  * This is an example of a monitoring bean which exposes parameters to CruxConsole (Monitoring)
  *
  * @author psp
  * @since 2016.07.15
  */
@MonitoringBean(domain = "Hello", name = "Message statistics")
class Monitoring(formatter: Formatter) {
  @MonitoredAttribute(name = "Last invocation")
  def getLastInvocation: Iterable[Invocation]= formatter.invocationStats.map(e => Invocation(e._1, e._2.toString))

  @MonitoredAttribute(name = "Unresolved languages")
  def getUnresolvedLanguages: Iterable[String] = formatter.unresolved.keys.map(_.toString)

  case class Invocation(language: Lang, time: String)

}
