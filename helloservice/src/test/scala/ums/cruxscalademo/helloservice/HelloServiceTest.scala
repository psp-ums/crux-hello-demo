package ums.cruxscalademo.helloservice

import cellvision.crux.queue.{QueueId, CruxQueueService}
import cellvision.crux.test.spring.JavaConfigContextLoader
import org.junit.Assert._
import org.junit._
import org.junit.runner.RunWith
import org.mockito.Mockito._
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import ums.cruxscalademo.helloservice.internal.config.{HelloServiceConfig}

/**
  * Unit test for the entire bundle. Loads the bundle context overridden with the test bundle context. Use @Autowired var's
  * for getting the reference to beans from the bundle context.
  *
  * @author psp
  * @since 2016.07.14
  */
@RunWith(classOf[SpringJUnit4ClassRunner])
@ContextConfiguration(locations = Array(
  "ums.cruxscalademo.helloservice.internal.spring.BundleContext",
  "ums.cruxscalademo.helloservice.spring.TestBundleContext"),
  loader = classOf[JavaConfigContextLoader])
class HelloServiceTest {
  @Autowired var service: HelloService = _
  @Autowired var config: HelloServiceConfig = _
  @Autowired var cruxQueueService: CruxQueueService = _

  @Test
  def testGet: Unit = {
    assertEquals(HelloMessage("Hello World From Crux"), service.getHelloWorldGreeting(Lang.GB))
    assertEquals(HelloMessage("Hallo Verden Fra Crux"), service.getHelloWorldGreeting(Lang.NO))
    }

  @Test
  def testPrint: Unit = {
    service.printHelloWorld(Lang.GB)
    verify(cruxQueueService).send(QueueId("Q_PRINT"), HelloMessage("Hello World From Crux"))
    service.printHelloWorld(Lang.NO)
    verify(cruxQueueService).send(QueueId("Q_PRINT"), HelloMessage("Hallo Verden Fra Crux"))

  }


}
