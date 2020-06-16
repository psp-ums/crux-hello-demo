package ums.cruxscalademo.helloweb.web

import cellvision.crux.web.{CruxServletContext, CruxWebAppInitializer}
import org.eclipse.jetty.servlet.DefaultServlet
import org.springframework.web.context.{ContextLoaderListener, WebApplicationContext}

/**
  * Web jar version of web.xml
  *
  * @author psp
  * @since 2016.11.03
  */
class WebInitializer extends CruxWebAppInitializer {

  @Override
  def onStartup(context: CruxServletContext, springContext: WebApplicationContext) {
    context.addServlet(new ums.cruxscalademo.helloweb.HelloServlet(), Array[String]("/api"), Some("HelloServlet"))
    context.addEventListener(new ContextLoaderListener(springContext))
    context.setBasicAuthentication("HelloRealm")
    context.setDefaultFileRealmConfig("hello_realm.properties")
    context.addSecurityConstraint("/api", "user")
    context.addServlet(new DefaultServlet(), Array("/"))
  }
}
