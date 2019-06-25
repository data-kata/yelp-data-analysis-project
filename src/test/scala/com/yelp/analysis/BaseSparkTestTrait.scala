package com.yelp.analysis

import org.scalatest.{BeforeAndAfterEach, FlatSpec}

/**
 * @author Oleksandr Avlesi
 */
trait BaseSparkTestTrait extends FlatSpec with BeforeAndAfterEach {

  override def beforeEach(): Unit = {
    super.beforeEach()
  }

  override def afterEach(): Unit = {
    super.afterEach()
  }
}
