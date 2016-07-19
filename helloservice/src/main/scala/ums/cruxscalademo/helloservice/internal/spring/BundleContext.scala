package ums.cruxscalademo.helloservice.internal.spring

import cellvision.crux.log.{CruxLog, CruxLogger}
import cellvision.crux.queue.CruxQueueService
import cellvision.crux.service.CruxServiceRef
import org.springframework.context.annotation.{Bean, Configuration}
import ums.cruxscalademo.helloservice.internal.HelloServiceImpl
import ums.cruxscalademo.helloservice.internal.config.HelloServiceConfig
import ums.cruxscalademo.helloservice.internal.formatting.{Formatter, Monitoring, Selftester}
import ums.cruxscalademo.helloservice.internal.queue.PrintQueueReceiver
import ums.cruxscalademo.helloservice.internal.timer.HelloTimer

/**
  * The Spring bundle context is automatically detected by Crux when it is contained in a spring package
  *
  * @author psp
  * @since 2016.07.14
  */
@Configuration
class BundleContext {
  @Bean def service = new HelloServiceImpl(formatter, cruxQueueService, logger)

  @Bean def config = new HelloServiceConfig

  @Bean def logger: CruxLog = CruxLogger("ums.cruxscalademo.HelloService")

  @Bean def formatter: Formatter = new Formatter(config)

  @Bean def printQueueRecever: PrintQueueReceiver = new PrintQueueReceiver(logger)

  @Bean def cruxQueueService: CruxQueueService = CruxServiceRef[CruxQueueService]

  @Bean def selftester: Selftester = new Selftester(formatter)

  @Bean def monitoring: Monitoring = new Monitoring(formatter)

  @Bean def timer: HelloTimer = new HelloTimer(service, logger)
}