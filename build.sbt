val zioVersion            = "2.0.20"
val zioJsonVersion        = "0.6.2"
val zioConfigVersion      = "3.0.7"
val logbackClassicVersion = "1.4.7"
val postgresqlVersion     = "42.6.0"
val testContainersVersion = "0.40.17"
val zioLoggingVersion     = "2.1.12"
val flywayVersion         = "9.16.3"
val dockerReleaseSettings = Seq(
  dockerExposedPorts   := Seq(8080),
  dockerExposedVolumes := Seq("/opt/docker/logs"),
  dockerBaseImage      := "eclipse-temurin:17.0.4_8-jre"
)

val root = (project in file("."))
  .settings(
    inThisBuild(
      List(
        organization := "com.example",
        scalaVersion := "2.13.12"
      )
    ),
    name           := "zio-2.0.20-contains-bug",
    addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1"),
    libraryDependencies ++= Seq(
      // general
      "dev.zio"        %% "zio-json"            % zioJsonVersion,
      "dev.zio"        %% "zio"                 % zioVersion,
      "dev.zio"        %% "zio-test"            % zioVersion,
      "dev.zio"        %% "zio-config"          % zioConfigVersion,
      "dev.zio"        %% "zio-config-magnolia" % zioConfigVersion,
      "dev.zio"        %% "zio-config-typesafe" % zioConfigVersion,
      "org.postgresql"  % "postgresql"          % postgresqlVersion,
      "org.flywaydb"    % "flyway-core"         % flywayVersion,

      // logging
      "dev.zio"             %% "zio-logging"       % zioLoggingVersion,
      "dev.zio"             %% "zio-logging-slf4j" % zioLoggingVersion,
      "ch.qos.logback"       % "logback-classic"   % logbackClassicVersion,

      // test
      "dev.zio"            %% "zio-test-sbt"                    % zioVersion            % Test,
      "com.dimafeng"       %% "testcontainers-scala-postgresql" % testContainersVersion % Test
    ),
    testFrameworks := Seq(new TestFramework("zio.test.sbt.ZTestFramework")),
    dockerReleaseSettings
  )
  .enablePlugins(DockerPlugin, JavaAppPackaging)
