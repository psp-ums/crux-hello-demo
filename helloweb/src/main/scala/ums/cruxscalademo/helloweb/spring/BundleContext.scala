package ums.cruxscalademo.helloweb.spring

import cellvision.crux.log.CruxLogFactory
import cellvision.crux.service.CruxServiceRef
import org.apache.commons.logging.Log
import org.springframework.context.annotation.{Bean, Configuration}
import ums.cruxscalademo.helloservice.HelloService
import ums.cruxscalademo.helloweb.config.HelloWebConfig

@Configuration
class BundleContext {

  @Bean def config: HelloWebConfig = new HelloWebConfig()

  @Bean def logger: Log = CruxLogFactory("ums.cruxscalademo.HelloWeb")

  @Bean def helloService: HelloService = CruxServiceRef[HelloService]

}