// Turn this project into a Scala.js project by importing these settings
enablePlugins(ScalaJSPlugin)

name := "Example"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.8"

persistLauncher in Compile := true

persistLauncher in Test := false

testFrameworks += new TestFramework("utest.runner.Framework")

libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.9.0",
    "org.singlespaced" %%% "scalajs-d3" % "0.3.1",
    "com.lihaoyi" %%% "utest" % "0.4.3" % "test"
)

mainClass in Compile := Some("example.ScalaJSExample5")


