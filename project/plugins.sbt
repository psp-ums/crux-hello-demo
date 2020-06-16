resolvers += "UMS Maven repository" at "https://repo.ums2.no/maven/repo"

credentials += Credentials("Restricted", "repo.ums2.no", "repo", "7pTPhdwx67zNDE4J")

resolvers += Resolver.jcenterRepo

addSbtPlugin("ums.sbt.plugin" % "sbt-ums" % "3.6.1")

addSbtPlugin("io.get-coursier" % "sbt-coursier" % "1.0.0-RC13")

addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.5.1")

addSbtPlugin("com.github.kxbmap" % "sbt-jooq" % "0.4.0")

addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.9.0")

addSbtPlugin("com.earldouglas" % "xsbt-web-plugin" % "4.0.2")

libraryDependencies += "org.vafer" % "jdeb" % "1.6" artifacts Artifact("jdeb", "jar", "jar")

// Add Jaxb apis for Java 9
libraryDependencies += "javax.xml.bind" % "jaxb-api" % "2.3.0"

libraryDependencies += "com.sun.xml.bind" % "jaxb-impl" % "2.3.0"

libraryDependencies += "com.sun.xml.bind" % "jaxb-core" % "2.3.0"

