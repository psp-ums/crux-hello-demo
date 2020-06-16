import java.io.File
import scala.io.Source

import Dependencies.depsTest
import com.earldouglas.xwp.WebappPlugin.autoImport.{webappPostProcess, webappWebInfClasses}
import sbt.Keys._
import sbt.Package.ManifestAttributes
import sbt.{Credentials, _}
import ums.sbt.plugin.CruxKeys._
import ums.sbt.plugin.CruxModulePlugin

object Common {

  // Common settings
  val commonSettings: Seq[Def.Setting[_]] = Seq(
    cruxVersion := Dependencies.cruxVersion,
    version := Source.fromFile(file("project/version")).mkString.trim,
    scalaVersion := Dependencies.scalaVersion,
    shellPrompt := buildShellPrompt,
    cruxAppName := "crux-hello-demo",
    appDir := "hello",
    organization := "ums.cruxdemo.hello",
    javacOptions += "-Xlint:-options",
    javacOptions += "-Xlint:deprecation",
    javacOptions += "-Xlint:unchecked",
    javacOptions ++= Seq[String]("-encoding", "UTF-8"),
    javacOptions += "-g:source,lines,vars",

    resolvers += "UMS Maven Repository" at "https://repo.ums2.no/maven/repo",
    resolvers += "Clojars" at "https://clojars.org/repo/",
    resolvers += Resolver.jcenterRepo,

    credentials += Credentials("Restricted", "repo.ums2.no", "repo", "7pTPhdwx67zNDE4J"),

    scalacOptions ++= Seq[String]("-deprecation"),
    scalacOptions ++= Seq[String]("-encoding", "UTF-8"),
    packageOptions += ManifestAttributes(
      ("Built-By", System.getProperty("user.name")),
      ("Build-Time", new java.util.Date().toString)
    ),
    publishTo := Some(Resolver.ssh("UMS Maven Repo", "192.168.3.105", "maven/repo")
            .as(System.getProperty("publish.username", "mservices"), System.getProperty("publish.password", ""))
            .withPublishPermissions("0644")),
    publishArtifact in(Compile, packageDoc) := false,
    publishArtifact in packageDoc := false,
    sources in(Compile, doc) := Seq.empty,

    libraryDependencies += "cellvision.crux" % "crux-distribution" % cruxVersion.value % "provided",
    libraryDependencies ++= depsTest.map(_ % "test")
  )

  // Settings for creating .war-files and including them in .car and install
  val commonWarSettings: Seq[Def.Setting[_]] = commonSettings ++ Seq(
    packageBin := sbt.Keys.`package`.value,

    cruxInstallFile := cruxInstallDir.value / (name.value + ".war"),
    cruxInstall := CruxModulePlugin.install(
      sbt.Keys.`package`.value,
      cruxInstallFile.value,
      target.value,
      streams.value.log),

    exportedPackages := Nil,

    webappWebInfClasses := true,
    webappPostProcess := {
      webappDir: File =>
        val libDir: File = webappDir / "WEB-INF/lib/"
        libDir.listFiles().foreach(IO.delete)
    }
  )

  def buildShellPrompt(state: State): String = {
    val extracted = Project.extract(state)
    val projectName = extracted.getOpt(name).getOrElse(extracted.currentProject.id)
    val projectVersion = extracted.getOpt(version).getOrElse("UNVERSIONED")
    import scala.Console._
    s"$BLUE[$CYAN$projectName$WHITE-$YELLOW$projectVersion-$GREEN$gitBranch$BLUE] $RESET"
  }

  def filePlan(): Map[String, String] = Map[String, String](
    s"ums.cruxdemo.hello:helloservice:*" -> "mod",
    s"ums.cruxdemo.hello:helloweb:*" -> "mod",
    "*:*:*" -> null
  )

  private def gitBranch = scala.sys.process.Process("git rev-parse --abbrev-ref HEAD").lineStream.head

}
