import re
from pyspark import SparkConf, SparkContext

'''Objectives:
`word-count.py`: From text files to list of words
`word-count-better.py`: trim punctuation and non-character with regular expressions 
'''


def normalizeWords(text):
    return re.compile(r'\W+', re.UNICODE).split(text.lower())


conf = SparkConf().setMaster("local").setAppName("WordCount")
sc = SparkContext(conf=conf)

raw_input = sc.textFile("book.txt")
words = raw_input.flatMap(normalizeWords)
wordCounts = words.countByValue()

for word, count in wordCounts.items():
    cleanWord = word.encode('ascii', 'ignore')
    if cleanWord:
        print(f'{cleanWord.decode()} {count}')
