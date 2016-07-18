package ums.cruxscalademo.helloservice.internal.formatting

import cellvision.crux.http.HttpResponseCodes
import cellvision.crux.soh.{SelftestResult, Id, Selftestable}

/**
  * This is an example of an selftestable bean which hooks into Crux Soh. The runSelftest method is invoked every five
  * seconds to check health. See CruxConsole -> Monitoring -> Soh.
  *
  * @author psp
  * @since 2016.07.15
  */
class Selftester(formatter: Formatter) extends Selftestable{
  @Id("UnresolvedLanguageCodes")
  override def runSelftest(): SelftestResult = {
    formatter.unresolved.keys.toList match {
      case Nil => new SelftestResult(HttpResponseCodes.HTTP_SUCCESS, "No unresolved language codes")
      case list => new SelftestResult(HttpResponseCodes.HTTP_NOT_FOUND, s"Unresolved codes: ${list.mkString(",")}")
    }
  }

}
