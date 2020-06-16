import scala.collection.Seq
import sbt._

/**
 * <p>Dependencies</p>
 *
 * @author psp
 * @since 2020.06.15
 */
object Dependencies {
  val scalaVersion = "2.12.8"
  val cruxVersion = "3.8.0"
  val springVersion = "4.3.3.RELEASE"

  val depsTest = Seq(
    "junit" % "junit" % "4.12",
    "org.mockito" % "mockito-core" % "1.9.5",
    "org.scalatest" %% "scalatest" % "3.0.4",
    "com.novocode" % "junit-interface" % "0.11",
    "easymock" % "easymock" % "2.2",
    "org.springframework" % "spring-test" % springVersion ,
    "org.scalactic" %% "scalactic" % "3.0.4",
    "cellvision.crux" % "crux-test" % cruxVersion
  )


}
