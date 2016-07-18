package ums.cruxscalademo.helloservice.spring

import cellvision.crux.log.CruxLog
import cellvision.crux.queue.CruxQueueService
import org.apache.commons.logging.Log
import org.apache.commons.logging.impl.SimpleLog
import org.mockito.Mockito._
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.{Bean, Configuration}
import ums.cruxscalademo.helloservice.internal.config.HelloServiceConfig
import cellvision.crux.log.LoggerConversion._

/**
  * This is a spring context that is used for the unit tests only. It overrides the real bundle context. All ServiceRef's
  * need to be overridden in the test bundle context.
  *
  * @author psp
  * @since 2016.07.14
  */
@Configuration
class TestBundleContext {
  @Autowired
  var config: HelloServiceConfig = _

  @Bean
  def logger: CruxLog = {
    val l = new SimpleLog("Test"); l.setLevel(SimpleLog.LOG_LEVEL_ALL); l
  }

  @Bean
  def cruxQueueService: CruxQueueService = mock(classOf[CruxQueueService])
}