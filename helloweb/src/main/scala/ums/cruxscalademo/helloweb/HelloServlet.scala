package ums.cruxscalademo.helloweb

import java.io.PrintWriter
import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

import cellvision.crux.http.HttpResponseCodes
import org.apache.commons.logging.Log
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.support.WebApplicationContextUtils
import ums.cruxscalademo.helloservice.Lang.Lang
import ums.cruxscalademo.helloservice.{HelloService, Lang, UnknownLanguageException}
import ums.cruxscalademo.helloweb.config.HelloWebConfig

import scala.util.Try

/**
  * The servlet implementation. Retrieves the beans from the bundle context on init and store them in var fields
  *
  * @author psp
  * @since 2016.07.14
  */
class HelloServlet extends HttpServlet {
  private var helloService: HelloService = null
  private var config: HelloWebConfig = null
  private var logger: Log = null

  override def init() {
    super.init()
    val ctx: WebApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext)
    logger = ctx.getBean("logger").asInstanceOf[Log]
    config = ctx.getBean("config").asInstanceOf[HelloWebConfig]
    helloService = ctx.getBean("helloService").asInstanceOf[HelloService]
  }

  override def doPost(request: HttpServletRequest, response: HttpServletResponse) {
    val (code, message) = getLang(request) match {
      case Some(l) =>
        invokeService(_.printHelloWorld(l))
      case None =>
        (HttpResponseCodes.HTTP_BAD_REQUEST, s"Missing or unknown language parameter 'lang' in request: ${request.getParameter("lang")}")
    }
    createHttpRespons(response, code, message)
    logger.debug(s"Processed POST with lang parameter ${request.getParameter("lang")}. Result $code - $message")
  }

  override def doGet(request: HttpServletRequest, response: HttpServletResponse) {
    val (code, message) = getLang(request) match {
      case Some(l) => invokeService(_.getHelloWorldGreeting(l).message)
      case None => (HttpResponseCodes.HTTP_BAD_REQUEST, "Missing or unknown language parameter 'lang' in request")
    }
    createHttpRespons(response, code, message)
    logger.debug(s"Processed GET with lang parameter ${request.getParameter("lang")}. Result $code - $message")
  }

  private def invokeService[T](op: HelloService => T): (Int, String) = {
    try {
      val result = op(helloService)
      (HttpResponseCodes.HTTP_SUCCESS, result.toString)
    } catch {
      case ul: UnknownLanguageException => (HttpResponseCodes.HTTP_NOT_FOUND, s"${ul.getMessage}")
      case e: Exception => (HttpResponseCodes.HTTP_INTERNAL_SERVER_ERROR, s"Unknown problem occurred: ${e.getMessage}")
    }
  }

  private def getLang(request: HttpServletRequest): Option[Lang] = {
    Option(request.getParameter("lang")).flatMap(o => Try(Lang.withName(o)).toOption)
  }

  private def createHttpRespons(response: HttpServletResponse, code: Int, message: String): Unit = {
    response.setStatus(code)
    response.setContentLength(message.length)
    response.setContentType(config.contentType)
    val printwriter: PrintWriter = response.getWriter
    printwriter.print(message)
    printwriter.flush()
    printwriter.close()
  }


}