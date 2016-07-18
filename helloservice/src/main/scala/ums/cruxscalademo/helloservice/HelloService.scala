package ums.cruxscalademo.helloservice

import cellvision.crux.annotation.Service
import ums.cruxscalademo.helloservice.Lang._

/**
  * Service interface that is automatically registered into the Crux service repository when the implementing class is
  * in the bundle context.
  *
  * @author psp
  * @since 2016.07.14
  */
@Service
trait HelloService {
  def printHelloWorld(lang: Lang): Unit

  def getHelloWorldGreeting(lang: Lang): HelloMessage

}
