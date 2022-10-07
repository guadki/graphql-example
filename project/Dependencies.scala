import sbt._

object Dependencies {

  private val sangriaVersion = "3.3.0"
  private val akkaVersion = "2.6.19"
  private val sangriaAkkaHttpVersion = "0.0.3"

  val sangria = "org.sangria-graphql" %% "sangria" % sangriaVersion
  val sangriaAkkaHttpCore = "org.sangria-graphql" %% "sangria-akka-http-core" % sangriaAkkaHttpVersion
  val sangriaAkkaHttpCirce = "org.sangria-graphql" %% "sangria-akka-http-circe" % sangriaAkkaHttpVersion
  val akkaHttp = "com.typesafe.akka" %% "akka-http" % "10.2.10"
  val akkaActor = "com.typesafe.akka" %% "akka-actor" % akkaVersion
  val akkaStreams = "com.typesafe.akka" %% "akka-stream" % akkaVersion


}
