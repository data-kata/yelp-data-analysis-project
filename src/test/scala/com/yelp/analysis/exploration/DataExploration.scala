package com.yelp.analysis.exploration

import com.yelp.analysis.BaseSparkTestTrait
import com.yelp.analysis.utils.DataUtils
import org.apache.spark.sql.functions.{col,lit}

/**
 * @author Oleksandr Avlesi
 */
class DataExploration extends BaseSparkTestTrait {
  override def name = "DataExploration"

  val businessInput: String = getClass.getResource("/yelp-data/yelp_academic_dataset_business.json").getPath
  val reviewInput: String = getClass.getResource("/yelp-data/yelp_academic_dataset_review.json").getPath
  val userInput: String = getClass.getResource("/yelp-data/yelp_academic_dataset_user.json").getPath

  it should "explore yelp input data" in {

    println("Business dataset")
    val businessDf = DataUtils.read(businessInput, "json", None)
    // businessDf.show(false)

    println("Review dataset")
    val reviewDf = DataUtils.read(reviewInput, "json", None)
    // reviewDf.show()

    println("User dataset")
    val userDf = DataUtils.read(userInput, "json", None)
    // userDf.show()

    val givenBusinessId = "1SWheh84yJXfytovILXOAQ"
    val businessIdCol = "business_id"
    val userIdCol = "user_id"
    println("Find all users that left a review for a hotel having ... BI")
    businessDf
      .join(reviewDf, businessDf.col(businessIdCol) === reviewDf.col(businessIdCol))
      .join(userDf, reviewDf.col(userIdCol) === userDf.col(userIdCol))
      .where(businessDf.col(businessIdCol) === givenBusinessId)
      .select(userDf.col("name"))
      .show(false)
  }
}
