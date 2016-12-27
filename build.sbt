import sbt._
import Keys._

lazy val `play_it_forward` = (project in file("PlayItForward")).enablePlugins(PlayScala)
  .settings(Common.avroSettings:_*)
  .settings(Common.commonSettings:_*)
  .settings(Common.scalaSettings:_*)
  .settings(libraryDependencies ++= Dependencies.playItForward)

enablePlugins(sbtdocker.DockerPlugin, JavaAppPackaging)

// http://www.scala-sbt.org/sbt-native-packager/recipes/play.html#build-configuration
// Make docker depend on the package task, which generates a jar file of the application code
docker <<= docker.dependsOn(Keys.`package`.in(Compile, packageBin))

// Define a dockerfile, using parameters from native-packager
dockerfile in docker := {
  val appDir = stage.value
  val targetDir = "/app"

  new Dockerfile {
    from("java")
    entryPoint(s"$targetDir/bin/${executableScriptName.value}")
    copy(appDir, targetDir)
  }
}

buildOptions in docker := BuildOptions(cache = false)
unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )
routesGenerator := InjectedRoutesGenerator