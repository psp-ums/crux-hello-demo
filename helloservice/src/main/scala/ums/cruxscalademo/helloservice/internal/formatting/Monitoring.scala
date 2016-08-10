package ums.cruxscalademo.helloservice.internal.formatting

import cellvision.crux.monitoring.{Command, MonitoredAttribute, MonitoringBean}
import ums.cruxscalademo.helloservice.HelloService
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

  @MonitoredAttribute(name = "Reset statistics")
  def resetStatistics(): Command =
    new Command(classOf[HelloService], "resetStatistics")



  case class Invocation(language: Lang, time: String)

}
