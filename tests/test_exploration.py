import pytest
from pyspark.sql import functions as F
from pyspark.sql.session import SparkSession

from yelp.exploration import find_most_popular_category

pytestmark = pytest.mark.usefixtures("spark_test_session")


class TestMission(object):

    # def test_with_life_goal(self, spark_test_session):
    #     source_data = [
    #         ("jose", 1),
    #         ("li", 2)
    #     ]
    #     source_df = spark_test_session.createDataFrame(
    #         source_data,
    #         ["name", "age"]
    #     )
    #
    #     actual_df = with_life_goal(source_df)
    #
    #     expected_data = [
    #         ("jose", 1, "escape!"),
    #         ("li", 2, "escape!")
    #     ]
    #     expected_df = spark_test_session.createDataFrame(
    #         expected_data,
    #         ["name", "age", "life_goal"]
    #     )
    #
    #     assert(expected_df.collect() == actual_df.collect())

    # def test_exploration(self, spark_test_session):
    #     show_first_lines(spark_test_session)
    #
    # def test_analyze(self, spark_test_session):
    #     analyze_train(spark_test_session)

    def test_find_most_reviewed_category(self, spark_test_session):
        find_most_popular_category(spark_test_session)



