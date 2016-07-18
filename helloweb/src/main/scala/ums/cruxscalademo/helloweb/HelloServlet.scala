package ums.cruxscalademo.helloweb

import java.io.PrintWriter
import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}
import cellvision.crux.http.HttpResponseCodes
import org.apache.commons.logging.Log
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.support.WebApplicationContextUtils
import ums.cruxscalademo.helloservice.{Lang, UnknownLanguageException, HelloService}
import ums.cruxscalademo.helloweb.config.HelloWebConfig

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
    logger.debug("POST request ")
    doGet(request, response)
  }

  override def doGet(request: HttpServletRequest, response: HttpServletResponse) {
    val lang = request.getParameter("lang")
    val (code, message) = lang match {
      case l: String => (HttpResponseCodes.HTTP_SUCCESS, helloService.getHelloWorldGreeting(Lang.withName(lang))message)
      case _ =>  (HttpResponseCodes.HTTP_BAD_REQUEST, "Missing language parameter 'lang' in request")
    }
    response.setStatus(code)
    response.setContentLength(message.length)
    response.setContentType(config.contentType)
    val printwriter: PrintWriter = response.getWriter
    printwriter.print(message)
    printwriter.flush()
    printwriter.close()
    }

  private def getParameter(request: HttpServletRequest, name: String): String = {
    val value: String = request.getParameter(name)
    if (value == null || value.isEmpty) {
      throw new IllegalArgumentException("Required parameter is missing: " + name)
    }
    value
  }
}