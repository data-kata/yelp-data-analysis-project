import pyspark.sql.functions as F
from pyspark.sql.functions import col, explode
from pyspark.sql.types import ArrayType
from pyspark.sql.types import StringType
from pyspark.sql.types import StructField
from pyspark.sql.types import StructType


def find_most_popular_category(spark):
    business_schema = StructType([
        StructField('city', StringType()),
        StructField('neighborhoods', StringType()),
        StructField('open', StringType()),
        StructField('business_id', StringType()),
        StructField('full_address', StringType()),
        StructField('state', StringType()),
        StructField('longitude', StringType()),
        StructField('latitude', StringType()),
        StructField('type', StringType()),
        StructField('categories', ArrayType(StringType(), containsNull=False), True)
    ])
    business_df = spark.read.json('resources/yelp_test_set_business.json', business_schema)

    # add exploded categories column
    exploded_cat_df = business_df.withColumn('cat', explode('categories'))
    print(f'columns in business set are {str(exploded_cat_df.columns)}')

    print('print 10 rows with exploded categories')
    for row in exploded_cat_df.limit(10).collect():
        print(row.cat)

    review_schema = StructType([
        StructField('user_id', StringType()),
        StructField('business_id', StringType()),
        StructField('type', StringType())
    ])

    review_df = spark.read.json('resources/yelp_test_set_review.json', review_schema)
    print(f'columns in review set are {str(review_df.columns)}')
    review_business_df = review_df.join(exploded_cat_df, ['business_id'])

    # goal is to have most popular categories done with users from the city x
    given_city = 'Chandler'
    res = review_business_df.filter(col('city') == given_city) \
        .groupBy('cat') \
        .agg(F.count(F.lit(1)).alias('CatNumber')) \
        .sort(col('CatNumber').desc())

    res.show()
