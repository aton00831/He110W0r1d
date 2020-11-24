from pyspark.sql import SparkSession
from pyspark.sql import functions as func

'''
# In this case: 
> Not Very Straightforward


## Review: (Using Traditional RDD)
1. create SparkContext 
```pyspark
conf = SparkConf().setMaster("local").setAppName("WordCount")
sc = SparkContext(conf=conf)
```
2. flatMap (split -> lower)

## SQL
1. create SparkSession
```pyspark
spark = SparkSession.builder.appName("WordCount").getOrCreate()
```
2. explode (explodes columns into rows)

## Notes:
- DataFrame: passing columns as parameters (magical, like pandas series)
- Using DataFrames with this unstructured text data isn't a great fit
- Our initial DataFrame will just have Row objects with a column named "value" for each line of text
- `word-count` is a case where RDDs could be more straightforward

## Suggestion:
Sometimes it makes sense to load your data as an Rdd, then convert to a DataFrame for further processing later

DataFrame <-> RDD, YOU CAN USE BOTH
(SQL Queries) vs (Map Reduce)

most of time sql is the right tool for analytics jobs 
(and it give spark opportunities to optimize)
'''


spark = SparkSession.builder.appName("WordCount").getOrCreate()


# Read each line of my book into a dataframe
inputDF = spark.read.text("./data/book.txt")

# Split using a regular expression that extracts words
words = inputDF.select(func.explode(func.split(inputDF.value, "\\W+")).alias("word"))
words.filter(words.word != "")

# Normalize everything to lowercase
lowercaseWords = words.select(func.lower(words.word).alias("word"))

# Count up the occurrences of each word
wordCounts = lowercaseWords.groupBy("word").count()

# Sort by counts
wordCountsSorted = wordCounts.sort("count")

# Show the results.
wordCountsSorted.show(wordCountsSorted.count())
