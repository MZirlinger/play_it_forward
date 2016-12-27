import play.sbt.PlayImport.{cache, jdbc, specs2, ws}

object Dependencies {
  import Library._

  val playItForward =
    Seq(
      jdbc,
      cache,
      ws,
      specsTest
    ) ++ commonLib
}

object Library {
  import sbt._

  val kafka = "org.apache.kafka" %% "kafka" % Versions.Modules.kafka
  val kafkaClients = "org.apache.kafka" %% "kafka-clients" % Versions.Modules.kafka
  val kafkaSchemaRegistry = "io.confluent" %% "kafka-schema-registry" % Versions.Modules.confluent
  val kafkaSchemaRegistryClient = "io.confluent" %% "kafka-schema-registry-client" % Versions.Modules.confluent
  val kafkaStreams = "io.confluent" %% "kafka-streams" % Versions.Modules.confluent exclude ("com.sun.jmx", "jmxri") exclude ("com.sun.jdmk", "jmxtools") exclude ("javax.jms", "jms")
  val specsTest = specs2 % Test

  val commonLib = Set()
}