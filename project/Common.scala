object Common {
  import sbt._
  import sbt.Keys._
  import sbtavro.SbtAvro._

  lazy val avroSettings = sbtavro.SbtAvro.avroSettings ++ Seq(
    (stringType in avroConfig) := "String",

    (version in avroConfig) := "1.8.1"
  )

  lazy val commonSettings = Seq(
    description := "play it forward",
    name := "data-pipeline",
    organization := "com.mzirlinger",
    resolvers += "confluent" at "http://packages.confluent.io/maven",
    version := "1.0.0"
  )

  lazy val javacOptions = Seq(
    "-source", "1.8",
    "-target", "1.8"
  )

  lazy val scalaFmtConfig = Some(Resolver.file(".scalafmt"))

  lazy val scalaSettings = Seq(
    scalaVersion := "2.11.7",
    scalacOptions ++=
      Seq(
        "-deprecation",
        "-encoding", "utf8",
        "-feature",
        "-unchecked",
        "-Xfuture",
        "-Yno-adapted-args",
        "-Ywarn-dead-code",
        "-Ywarn-numeric-widen",
        "-Ywarn-value-discard",
        "-Ywarn-unused"
      )
  )
}

