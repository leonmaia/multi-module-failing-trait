import sbt.Keys._
import sbt._

object Simple extends Build {

  lazy val live = Project(
    id = "simple",
    base = file("."),
    settings = Project.defaultSettings ++ sharedSettings).settings(
    name := "simple") aggregate(
    simpleAProject, simpleBProject
    )

  private val simpleA: String = "simple-a"
  lazy val simpleAProject = Project(
    id = simpleA,
    base = file(simpleA),
    settings = Project.defaultSettings ++ sharedSettings).settings(
    name := simpleA
  ).dependsOn(simpleBProject)

  private val simpleB: String = "simple-b"
  lazy val simpleBProject = Project(
    id = simpleB,
    base = file(simpleB),
    settings = Project.defaultSettings ++ sharedSettings).settings(
    name := simpleB
  )

  lazy val testDependencies = Seq(
    "junit" % "junit" % "4.12" % "test",
    "org.mockito" % "mockito-core" % "1.10.19" % "test",
    "org.scalatest" %% "scalatest" % "2.2.5" % "test"
  )

  val sharedSettings = Seq(
    version := "1.0",
    organization := "com.simple",
    scalaVersion := "2.11.7",
    libraryDependencies ++= testDependencies
  )
}