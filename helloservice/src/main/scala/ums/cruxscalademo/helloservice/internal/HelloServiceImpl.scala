package ums.cruxscalademo.helloservice.internal

import cellvision.crux.log.CruxLog
import cellvision.crux.queue.CruxQueueService
import ums.cruxscalademo.helloservice.Lang.Lang
import ums.cruxscalademo.helloservice.internal.formatting.Formatter
import ums.cruxscalademo.helloservice.internal.queue.PrintQueueReceiver
import ums.cruxscalademo.helloservice.internal.queue.PrintQueueReceiver._
import ums.cruxscalademo.helloservice.{HelloMessage, HelloService}

/**
  * Implementation of the service interface.
  *
  * @author psp
  * @since 2016.07.14
  */
class HelloServiceImpl(formatter: Formatter, cruxQueueService: CruxQueueService, logger: CruxLog) extends HelloService {

  override def printHelloWorld(lang: Lang): Unit = {
    cruxQueueService.send(queueId, formatter.format(lang))
  }

  override def getHelloWorldGreeting(lang: Lang): HelloMessage = formatter.format(lang)

}
