import re
from pyspark import SparkConf, SparkContext
from pyspark.sql import SparkSession
from spacy.lang.en import English


'''Objectives:
`word-count.py`: From text files to list of words
`word-count-better.py`: trim punctuation and non-character with regular expressions
`word-count-better-sorted.py`: list word by count from high frequency to low
`word-count-beeter-sorted-nlp.py`: use nlp tools and SparkSession instead of SparkContext  
'''


def generateNormalizer(parser):
    # return lambda text: re.compile(r'\W+', re.UNICODE).split(text.lower())
    return lambda text: [str(word).lower() for word in parser(text)]


''' Use SparkSession instead of SparkContext
conf = SparkConf().setMaster("local").setAppName("WordCount")
sc = SparkContext(conf=conf)
'''

spark = SparkSession.builder.appName("WordCount").getOrCreate()
sc = spark.sparkContext

raw_input = sc.textFile("./data/book.txt")  # [sentence, ...]
nlp = English()
words = raw_input.flatMap(generateNormalizer(nlp))  # [word, ... ]

wordCounts = words.map(
    lambda word: (word, 1)  # {word: 1, ... }
).reduceByKey(lambda x, y: x + y)  # {word: sum(cnt), ... }

wordCountsSorted = wordCounts.map(
    lambda x: (x[1], x[0])  # from (word, count) tp (count, word)
).sortByKey()
results = wordCountsSorted.collect()

for result in results:
    count = str(result[0])
    cleanWord = result[1].encode('ascii', 'ignore')
    if cleanWord:
        print(f'{cleanWord.decode()} {count}')

