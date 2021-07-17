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
  "org.scalatest" %% "scalatest" % "3.2.9" % Test
)


