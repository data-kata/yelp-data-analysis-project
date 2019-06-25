
name := "yelp-data-analysis-project"

version := "0.1"

scalaVersion := "2.11.12"

val org: String = "com.yelp.analysis"

lazy val baseSettings = Seq(
  organization := s"$org",
  scalaVersion := "2.11.12",
  fork in Test := true,
  logBuffered in Test := false,
  parallelExecution in Test := false
)

lazy val dependencies = new {

  val sparkOrg: String = "org.apache.spark"
  val tsConfigV: String = "1.3.3"
  val sparkV: String = "2.3.3"
  val scalatestV: String = "3.0.5"

  lazy val tsConfig: ModuleID = "com.typesafe" % "config" % tsConfigV
  lazy val scalatest: ModuleID = "org.scalatest" %% "scalatest" % scalatestV
  lazy val sparkCore: ModuleID = sparkOrg %% "spark-core" % sparkV
  lazy val sparkSql: ModuleID = sparkOrg %% "spark-sql" % sparkV
  lazy val sparkStreaming: ModuleID = sparkOrg %% "spark-streaming" % sparkV
  
  def getConf: Seq[ModuleID] = Seq(tsConfig)
  
  def getTest: Seq[ModuleID] = Seq(scalatest).map(_ % "test")

  def getSparkBase: Seq[ModuleID] = Seq(sparkCore, sparkSql, sparkStreaming).map(_ % Provided)

}

lazy val root = (project in file("."))
  .settings(baseSettings ++ Seq(
    libraryDependencies ++= dependencies.getSparkBase,
    libraryDependencies ++= dependencies.getConf,
    libraryDependencies ++= dependencies.getTest
  ))