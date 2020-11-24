from pyspark import SparkConf, SparkContext

conf = SparkConf().setMaster("local").setAppName("FriendsByAge")
sc = SparkContext(conf=conf)


def parseLine(line):
    # userID,name,age,friends
    fields = line.split(',')
    age = int(fields[2])
    numFriends = int(fields[3])
    return (age, numFriends)


lines = sc.textFile("./data/fakefriends.csv")
# parsing (mapping) the input data
rdd = lines.map(parseLine)  # output is key/value pairs of (age, numFriends)
# count up sum of friends and number of entries per age
totalsByAge = rdd.mapValues(
    lambda x: (x, 1)
).reduceByKey(
    lambda x, y: (x[0] + y[0], x[1] + y[1])
)
'''
# rdd.mapValues(lambda x: (x, 1)): 
(33, 385) => (33, (385, 1))
(33, 2) => (33, (2, 1))
(55, 221) => (55, (221, 1))

# .reduceByKey(lambda x, y: (x[0] + y[0], x[1] + y[1]))
=> (33, (385+2, 1+1))
..
=> (55, (221, 1))
'''
averagesByAge = totalsByAge.mapValues(lambda x: x[0] / x[1])
results = averagesByAge.collect()
for result in results:
    print(result)
