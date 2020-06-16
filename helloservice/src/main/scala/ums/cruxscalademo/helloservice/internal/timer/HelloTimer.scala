package ums.cruxscalademo.helloservice.internal.timer

import cellvision.coreutil.annotation.Execute
import cellvision.crux.timer.{CruxTimerTask, TriggerType}
import org.apache.commons.logging.Log
import ums.cruxscalademo.helloservice.{HelloService, Lang}

/**
  * An example of a Crux timer task called "HelloPrinter". The values in @CruxTimerTask are default values.
  *
  * @author psp
  * @since 2016.07.19
  */
@CruxTimerTask(id = "HelloPrinter", defaultType = TriggerType.CRON, defaultSchedule = "0 0/5 * * * ?")
class HelloTimer(helloService: HelloService, logger: Log) {

  @Execute
  def executeTimer(): Unit = {
    helloService.printHelloWorld(Lang.NO)
    logger.info("Periodic hello world timer invoked")
  }

}
