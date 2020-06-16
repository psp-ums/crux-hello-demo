import scala.collection.Seq

import Common._
import Dependencies._
import ums.sbt.plugin.CruxKeys.{exportedPackages, _}

lazy val `crux-hello-demo` = (project in file("."))
        .settings(commonSettings)
        .aggregate(
          `helloservice`,
          `helloweb`,
          `hellodistro`
        )

lazy val `helloservice` = (project in file("helloservice"))
        .enablePlugins(CruxModulePlugin)
        .settings(commonSettings)
        .settings(
          exportedPackages := Seq("ums.cruxscalademo.helloservice")
        )

lazy val `helloweb` = (project in file("helloweb"))
        .enablePlugins(CruxModulePlugin)
        .settings(commonSettings)
        .dependsOn(`helloservice`)

lazy val `hellodistro` = (project in file("distro"))
        .enablePlugins(CruxDistroPlugin, CruxModulePlugin)
        .settings(
          cruxFilePlan := filePlan(),
          topLevelDirectory := None,
          maintainer := "peder.sperstad@everbrige.com"
        )
        .settings(commonSettings)
        .dependsOn(
          `helloservice`,
          `helloweb`
        )


