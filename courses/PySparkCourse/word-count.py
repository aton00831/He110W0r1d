from pyspark import SparkConf, SparkContext

'''Objectives:
`word-count.py`: From text files to list of words 
'''

conf = SparkConf().setMaster("local").setAppName("WordCount")
sc = SparkContext(conf=conf)

raw_input = sc.textFile("./data/book.txt")  # [Lines]
words = raw_input.flatMap(lambda x: x.split())  # [Lines] -> [List of Words]
wordCounts = words.countByValue()

for word, count in wordCounts.items():
    cleanWord = word.encode('ascii', 'ignore')
    if cleanWord:
        print(f'{cleanWord.decode()} {count}')
