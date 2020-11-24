import re
from pyspark import SparkConf, SparkContext


'''Objectives:
`word-count.py`: From text files to list of words
`word-count-better.py`: trim punctuation and non-character with regular expressions
`word-count-better-sorted.py`: list word by count from high frequency to low  
'''


def normalizeWords(text):
    return re.compile(r'\W+', re.UNICODE).split(text.lower())


conf = SparkConf().setMaster("local").setAppName("WordCount")
sc = SparkContext(conf=conf)

raw_input = sc.textFile("./data/book.txt")
words = raw_input.flatMap(normalizeWords)

wordCounts = words.map(
    lambda x: (x, 1)
).reduceByKey(
    lambda x, y: x + y
)

wordCountsSorted = wordCounts.map(
    lambda x: (x[1], x[0])  # from (word, count) tp (count, word)
).sortByKey()

results = wordCountsSorted.collect()

for result in results:
    count = str(result[0])
    cleanWord = result[1].encode('ascii', 'ignore')
    if cleanWord:
        print(f'{cleanWord.decode()} {count}')

