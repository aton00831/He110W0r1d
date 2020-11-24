from pyspark import SparkConf, SparkContext

conf = SparkConf().setMaster("local").setAppName("SpendByCustomerSorted")
sc = SparkContext(conf=conf)


def extractCustomerPricePairs(line):
    # customerId, productId, price
    fields = line.split(',')
    return (int(fields[0]), float(fields[2])) # (customer, price)


rawInput = sc.textFile("./data/customer-orders.csv")
mappedInput = rawInput.map(extractCustomerPricePairs)
totalByCustomer = mappedInput.reduceByKey(lambda x, y: x + y)  # (customer, sum price)

# Changed for Python 3 compatibility:
# flipped = totalByCustomer.map(lambda (x,y):(y,x))
flipped = totalByCustomer.map(lambda x: (x[1], x[0]))  # (price, customer)

totalByCustomerSorted = flipped.sortByKey()

results = totalByCustomerSorted.collect()
for result in results:
    print(result)
