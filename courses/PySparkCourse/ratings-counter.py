from pyspark import SparkConf, SparkContext
import collections

# spark-sumbit ratings-counter.py
# Do not set $SPARK_HOME if install through homebrew on zsh
# use absolute path instead

# Set up our context
conf = SparkConf().setMaster("local").setAppName("RatingsHistogram")
sc = SparkContext(conf = conf)

lines = sc.textFile("./ml-100k/u.data")
ratings = lines.map(lambda x: x.split()[2])
result = ratings.countByValue()

sortedResults = collections.OrderedDict(sorted(result.items()))
for key, value in sortedResults.items():
    print("%s %i" % (key, value))
