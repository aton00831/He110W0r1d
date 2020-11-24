# -*- coding: utf-8 -*-
"""
Created on Mon Sep  7 15:28:00 2020

@author: Frank
"""

from pyspark.sql import SparkSession
from pyspark.sql import functions as func
from pyspark.sql.types import StructType, StructField, IntegerType, LongType
import codecs

'''' Tried to make the results readable
Display movie names, not ID's (from the u.item file)
We could use a Dataset to map ID's to names
    Thrn join it with out ratings dataset
    But this comes with some unnecessary overhead (not so many names)
We could just keep a dictionary loaded in thedriver program
    Or we could even let spark automatically forward it to each executor when needed
    But what if the table were massive? we'd only want to transfer it once to each executor and keep it there

# Boardcasr Variables (and UDF's
- broadcast objects to the executors, s.t. they're always there whenever needed
- just use sc.broadcast() to ship off whatever you want
- Then use .value() to get the object back
- Use the broadcasted object however you want - nap functionsm UDF's whatever

using a dataset might quicker, broa
'''
def loadMovieNames():
    movieNames = {}
    # CHANGE THIS TO THE PATH TO YOUR u.ITEM FILE:
    with codecs.open("E:/SparkCourse/ml-100k/u.ITEM", "r", encoding='ISO-8859-1', errors='ignore') as f:
        for line in f:
            fields = line.split('|')
            movieNames[int(fields[0])] = fields[1]
    return movieNames

spark = SparkSession.builder.appName("PopularMovies").getOrCreate()

nameDict = spark.sparkContext.broadcast(loadMovieNames())

# Create schema when reading u.data
schema = StructType([ \
                     StructField("userID", IntegerType(), True), \
                     StructField("movieID", IntegerType(), True), \
                     StructField("rating", IntegerType(), True), \
                     StructField("timestamp", LongType(), True)])

# Load up movie data as dataframe
moviesDF = spark.read.option("sep", "\t").schema(schema).csv("file:///SparkCourse/ml-100k/u.data")

movieCounts = moviesDF.groupBy("movieID").count()

# Create a user-defined function to look up movie names from our broadcasted dictionary
def lookupName(movieID):
    return nameDict.value[movieID]

lookupNameUDF = func.udf(lookupName)

# Add a movieTitle column using our new udf
moviesWithNames = movieCounts.withColumn("movieTitle", lookupNameUDF(func.col("movieID")))

# Sort the results
sortedMoviesWithNames = moviesWithNames.orderBy(func.desc("count"))

# Grab the top 10
sortedMoviesWithNames.show(10, False)

# Stop the session
spark.stop()
