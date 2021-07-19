scalaVersion := "2.13.6"


ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.suprnation"
ThisBuild / scalaVersion := "2.13.6"

lazy val app = (project in file("."))
  .settings(
    name := "MinTrianglePath",
    assembly / mainClass := Some("triangles.MinTrianglePathApp"),
    assembly / assemblyJarName := "MinTrianglePathApp.jar"
  )

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-effect" % "3.1.1" withSources() withJavadoc(),
  "org.scalatest" %% "scalatest" % "3.2.9" % Test,
  "org.typelevel" %% "cats-effect-testing-scalatest" % "1.1.0" % Test,
)


