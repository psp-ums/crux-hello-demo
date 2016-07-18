package ums.cruxscalademo.helloservice.internal

import cellvision.crux.log.CruxLog
import cellvision.crux.queue.CruxQueueService
import ums.cruxscalademo.helloservice.Lang
import ums.cruxscalademo.helloservice.Lang.Lang
import ums.cruxscalademo.helloservice.internal.formatting.Formatter
import ums.cruxscalademo.helloservice.{HelloMessage, UnknownLanguageException, HelloService}
import ums.cruxscalademo.helloservice.Lang
import ums.cruxscalademo.helloservice.internal.config.HelloServiceConfig
import PrintQueueReceiver._

class HelloServiceImpl(formatter: Formatter, cruxQueueService: CruxQueueService, logger: CruxLog) extends HelloService {

  override def printHelloWorld(lang: Lang): Unit = {
    cruxQueueService.send(queueId, formatter.format(lang))
  }

  override def getHelloWorldGreeting(lang: Lang): HelloMessage = formatter.format(lang)

}
