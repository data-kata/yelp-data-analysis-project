package com.yelp.analysis.utils

import org.apache.spark.sql.{DataFrame, SparkSession}

/**
 * @author Oleksandr Avlesi
 */
object DataUtils {

  def read(filePath: String, formatType: String, options: Option[Map[String, String]] = None)(implicit spark: SparkSession): DataFrame = {
    options match {
      case Some(opts) => spark.read.options(opts).format(formatType).load(filePath)
      case          _ => spark.read.format(formatType).load(filePath)
    }
  }
}
