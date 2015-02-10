import NativePackagerKeys._

packageArchetype.java_application

name := """advicebot"""
version := "1.0"

scalaVersion := "2.11.5"

scalacOptions += "-deprecation"

libraryDependencies += "org.jsoup" % "jsoup" % "1.8.1"
libraryDependencies += "org.apache.opennlp" % "opennlp-tools" % "1.5.3"

// Web stuff
libraryDependencies ++= Seq(
  "com.twitter" % "finagle-http_2.11" % "6.24.0",
  "oauth.signpost" % "signpost-core" % "1.2",
  "org.twitter4j" % "twitter4j-stream" % "3.0.5"
)
