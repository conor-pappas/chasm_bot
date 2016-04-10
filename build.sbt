name := "chasm_bot"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "org.postgresql" % "postgresql" % "9.2-1003-jdbc4"
)     

play.Project.playScalaSettings
