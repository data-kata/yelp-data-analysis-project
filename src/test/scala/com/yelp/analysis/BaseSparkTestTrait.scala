package com.yelp.analysis

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession
import org.scalatest.{BeforeAndAfterEach, FlatSpec}

/**
 * @author Oleksandr Avlesi
 */
trait BaseSparkTestTrait extends FlatSpec with BeforeAndAfterEach {

  def name: String

  def master: String = "local[*]"

  val configs = Map(
    "master" -> "local[*]",
    "spark.sql.session.timeZone" -> "UTC"
  )

  implicit var spark: SparkSession = _

  override def beforeEach(): Unit = {

    val sc: SparkConf = new SparkConf()
      .setAppName(name)
      .setMaster(master)
      .setAll(configs)

    spark = SparkSession
      .builder()
      .config(sc)
      .getOrCreate()

    spark.sparkContext.setLogLevel("ERROR")
  }

  override def afterEach(): Unit = {
    spark.stop()
  }
}
