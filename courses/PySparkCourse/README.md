# About the Course
- Course [Link](https://www.udemy.com/course/taming-big-data-with-apache-spark-hands-on/) (Not recommend ..)
- Examples By  [Frank Kane](https://www.udemy.com/user/frank-kane-2/) 
- [Installing Spark](https://sundog-education.com/spark-python/))
> Remember to export SPARK_HOME or added to PATH: 
> 如果使用 Homebrew + ZSH, 不要加 SPARK_HOME 會有 Permission Issue, 
> 改成使用絕對路徑來執行: `/usr/local/Cellar/apache-spark/3.0.1/bin `

- Course's data source: [GroupLens](https://grouplens.org/) 


# Introduction to Spark
- A fast and general engine for large-scale data processing
- Run programs up to 100x faster than Hadoop MapReduce in memory or 10x faster on disk (actually 3x
- DAG Engine optimizes workflows
- Code in Python, Java, or Scala
- Built around ont main Concept: RDD (the Resilient Distributed Dataset)
- general-purpose & lightning fast cluster computing platform
- Spark 解決 Hadoop 只能 Batch Processing 的問題
- 集 streaming, machine learning, SQL workloads 於一身
- Spark 是可以獨立運作 (不需要 Hadoop) 因為他有自己的 cluster management system. 他只有用到 Hadoop 的 storage 功能

## Features
- In-memory computation
- lazy evaluation
- Fault Tolerance
- Immutable
- Persistence
- Partitioning
- Parallel  
- Location-Stickiness
- Coarse-grained Operation (Operation 大多作用在整個 RDD 非單個 element)
- Typed
- No limitation (RDD 數量沒有使用上限制)

### Who
> If you have a really massive data set that can represent anything, weblogs, genomics data, you name it,
> Spark can slice and dice that data up, it can distribute the processing amongst a huge cluster of computers
> and take a data analysis problem that's just too big to run on one machine and divide it and conquer it
> by splitting it up amongst multiple machines.

### What's new in Spark3?

- MLLib is deprecated (sort of, stop maintaining MLLig with RDD, but still support for dataframe)
- Spark 3 is faster (17x faster then 2)
- Python2 is deprecated
- no deep learning library but GPU instance support (capable to integrate Tensorflow)
- deep kubernetes support
- binary file support
- SparkGraph, cipher query language (SQL for graph structure, SparkGraph is the new graphX)
- ACID support in data lakes with Delta Lake (S3, unstructured csv .. )

### Competitor

- Batch Processing: Hadoop MapReduce
- Streaming: Apache Storm, Apache S4
- Interactive Processing: Apache Impala, Apache Tez 
- Graph Processing: Neo4j, Apache Giraph

### Spark vs Hadoop
> 大数据生态的两个主要部分是Hadoop软件框架和Spark内存级计算引擎。
> Hadoop包含四个项目：Hadoop common, HDFS, YARN和MapReduce。
- HDFS 用于存储数据，HDFS 文件被划分成区块分布在集群上
- YARN 用于管理集群资源（CPU和内存）和支持 Hadoop 的公共实用程序
- MapReduce是利用磁盘的高I/O操作实现并行计算的框架，但它在迭代计算中性能不足。
- Spark 的作用相当于 MapReduce，它是基于内存的计算引擎。Spark将迭代过程的中间数据缓存到内存中，根据需要多次重复使用。此外，Spark利用RDD结构提升了容错性能。
- Spark 才做得到 Real-time, MR 視作 Batch 
- Spark 适合用于多次操作特定数据量大的数据集的场合；数据量小且计算密集度大的场合，其性能提升相对较小
- **Hadoop MapReduce 每個 Task 都要把 Data 寫信 Disk, Next Iteration 時再把資料從 Disk Read 出來, 導致速度降低, Spark 會把常用資料放在 Memory**
- Hadoop 的 Mapper & Reducer 都會獨立出一個 JVM, Spark Task 都會在預先分配好的 JVM
- Hadoop 只支援 Map&Reduct 運算, Spark 還支援 Cogroup 等等
- 在 Spark 中, Task 被轉換成 RDD 上的 transformations 與 actions
- RDD 包含多個 Elements, 被畫分到不同 Node 上進行 Concurrent Jobs 的 dataset, 也會被分到 Memory 中, RDD 代表了已被分區, 不可變的, 能被幣型化操作的 dataset
- Node 發生錯誤時 RDD 根據 Lineage 自動計算恢復
- Spark 可以分為 1 個 Driver (工作用電腦或是網上機器, 在上面編寫 Spark Program) + N 個 executor (在 RDD 分部的各個節點上)
- 通過 SparkContext (SC) 連接 Spark Cluster, 創建 RDD, accumulator, broadcast variable
- Driver 會把任務分成小 task, 送到 Executor 執行, Executor 之間可以通信, 完成 task 後回傳給 Driver
---

## Architecture
- Driver Program (SparkContext in Python, Java, Scala run on master node, script)
- Cluster Manager (Spark (it's own CM), Yarn(Hadoop ecosystem))
- Multiple Executor include Cache, Tasks

### Spark Context, "sc"
- created by your driver program
- is responsible for **making RDDs** resilient and distributed
- creates RDDs
- the spark shell creates a "sc" object for you
- `sc.textFIle(" ... file:// or s3n:// or hdfs:// ... "`
- (old) create rdd from hive Hive: hiveCrx = HiveContext(sc)
- can also create from JDBC, Cassandra, HBase, Elasticsearch, JSON,CSV, sequence file, object file, various compressed formats ...

## Components Of Spark 
> real-time stream processing, interactive processing, graph processing, in-memory processing as well as batch processing.

- Spark Core = RDDs + Operations
	- Spark Core 被遷入在 RDD 設計當中, **RDD handles partitioning data across all the nodes in a cluster**.
	- Operations = Transformation + Action
	- I/O 功能的實作
- Spark SQL: run SQL/HQL
	- works to access structured and semi-structured information
	- support both streaming and historical data
	- cost based optimizer
	- compatible with Hive data
	- Support source: Hive, Avro, Parquet, ORC, JSON, and JDBC.
- Spark Streaming: analysis realtime data with small batch
- Spark MLLib
- Spark GraphX
- SparkR

## PySpark
> a python shell, use python to interact with Spark Process
> 
> Python 透過 Py4j 去啟動 JVM 並創建 Scala SparkContext 做操作
> 
> Run `SPARK_HOME/bin/spark-submit firstapp.py`

```python
class pyspark.SparkContext (
   master = None, # It is the URL of the cluster it connects to.
   appName = None, # Name of your job.
   sparkHome = None, # Spark installation directory.
   pyFiles = None, # The .zip or .py files to send to the cluster and add to the PYTHONPATH.
   environment = None, # Worker nodes environment variables.
   batchSize = 0, # The number of Python objects represented as a single Java object. Set 1 to disable batching, 0 to automatically choose the batch size based on object sizes, or -1 to use an unlimited batch size.
   serializer = PickleSerializer(), # RDD serializer.
   conf = None,  # An object of L{SparkConf} to set all the Spark properties.
   gateway = None, # Use an existing gateway and JVM, otherwise initializing a new JVM.
   jsc = None,  # The JavaSparkContext instance.
   profiler_cls = <class 'pyspark.profiler.BasicProfiler'> 
   # A class of custom Profiler used to do profiling (the default is pyspark.profiler.BasicProfiler
)
```

### Cons of Using Python
- Scala is probably a more popular choice with Spark
- Spark is built on Scala, so coding in Scala is "native" to spark
- new features, libraries tend to be Scala-first (SparkStreaming)
- Actually, Python code in spark looks a LOT like Scala code



---

# RDD
>  a distributed collection of elements across cluster nodes.
> RDD 就是 Spark 基礎的資料結構, 可以做以下類比
> 1. 一般 RDD 為 Python 的 List
> 2. Paired RDD 為 Python 的 Dict
> 3. DataFrame 為 Pandas 的 DataFrame
> Spark3 之後的趨勢為使用 DataFrame 為主, 但其底層仍舊是 RDD, 
> 兩著可以輕鬆做轉換, 如果是需要使用 Map-Reduce 的情境時可以回來使用 RDD

## 3 pillar of RDD
> fault-tolerant, read-only, partitioned collection of records
1. Dataset: fundamentally, it's am abstraction for a giant set of data
2. Distributed: handle failure of executor node, 
3. Resilient: 可以彈性調整在 Memory or Disk

## Ways to create Spark RDD
1. Parallelized collections : parallelize method
2. External Datasets :  textFile method, (URL or files -> collection of lines)
3. Existing RDDs

## RDDs operations
> 2 types of operations 
1. Transformation : **creates a new Spark RDD** from the existing one. Moreover, it passes the dataset to the function and returns new dataset. 
2. Action : **returns final result** to driver program or write it to the external data store.


### Transforming RDDs
> Apply on a RDD to create a new RDD

- `map(f, preservesPartitioning = False)` A new RDD is returned by applying a function to each element in the RDD.
- flatmap (map but larger or smaller, e.g. lines to words)
- filter (trim out information you dont need, e.g. filter out error in blog)
- distinct (get distinct value)
- sample (take a random sample, useful for testing)
- union, intersection, subtract, cartesian (between 2 RDD)

### Actions RDD
> instruct Spark to perform computation and 
> send the result back to the driver
> 
> Nothing actually happened in your driver program 
> until an action is called! (Scala function)

- `collect()` (All the elements in the RDD are returned.)
- `count()` (Number of elements in the RDD is returned.)
- `foreach(f)` (Returns only those elements which meet the condition of the function inside foreach)
- countByValue, take, top ...
- `min`, `max`: [Example max-temperatures](./max-temperatures.py), [Example min-temperatures](./min-temperatures.py)
- `groupby`, sort: [Example total-spent](./total-spent-by-customer-sorted.py), [Example word-count](./word-count-better-sorted.py)
- `cache()` Persist this RDD with the default storage level (MEMORY_ONLY). You can also check if the RDD is cached or not.

## Paired RDD 
> Paired RDD = Key-Value RDD, Some special operations are provided (Compare to general RDD)
> Those operations is useful to optimize programs (like parallel processing)
> 
> Create through **map function**, if `.map()` return ...
>   - return 1-D general RDD
>   - return 2-D Paired RDD

### Create Paired RDD
- call `.map()` function on a general RDD and return a tuple
- tuple[0] as the key
- [Example: friends-by-age](./friends-by-age.py)
- [Example: word-count-better-sorted](./word-count-better-sorted.py)

``` python
pairs = lines.map(lambda x: (x.split(" ")[0], x))
```

### Paired RDD Operations
- `groupByKey()` : groups all values with the same key : (transformation)
- `redeuceByKey(func)` : combines values with the same key using func : (transformation)
- `combinedByKey(ceateCombiner, mergeValue, mergeCombiners, partitioneer)` :  : (transformation)
- `mapValues(func)` : map apply on value : (transformation)
- `keys()` : return keys : (transformation)
- `values()` : return values : (transformation)
- `sortByKey()` : : (transformation)
- `countByKey()` : : (Action)
- `collectAsMap()` : : (Action)
- `lookup(key)` : : (Action)
- join, rightOuterJoin, leftOuterJoin, cogroup, subtractByKey : SQL Style joins on two key/value rdd : (transformation)

### Paired RDD Performance Improvement (Efficient)
> use `mapValues(func)` and `flatMapValues(func)` instead of map/flatmap if your transformation doesn't affect the keys
>
> It allows Spark to maintain the partitioning from your original RDD 
> instead of having to shuffle the data around and that can be very 
> expensive when you're running on a cluster.

---

---

# SparkSQL
> Most important component of Spark, because it includes the **dataframe API**
- Extends RDD to a "DataFrame" object
- Contain Row objects
- Can run SQL queries
- Can have a schema (leading to more efficient storage)
- R/W to JSON, Hive, Paraquet, csv
- Communicates with JDBC/ODBC, Tableau
- The trend in Spark is to use RDDs less, and DataFrames more.
- DataFrames allow for better interoperability (MLLib and Spark Streaming are moving toward using DataFrames instead of RDDs for their primary API)
- DataFrams simplify development (you can perform most SQL operations on a DataFrame with 1 line)

## SparkSQl Template
### Create from RDD: [SparkSQL](./spark-sql.py)
> create from RDD and Row
- create RDD (SparkContext)
- map to list of `Row`
- createDataFrame(Row of RDDs)

### Create through read: [SparkSQLDataFrame](./spark-sql-dataframe.py)
- `SparkSession.read()`

### Query
- Text `result = spark.sql("SELECT * FROM people WHERE age >= 13 AND age <= 19")`
- Func `schemaPeople.groupBy("age").count().orderBy("age").show()`
- GetResult `result.collect():`
- Show `show()`

### Turn RDD into DataFrame
- [friends-by-age](./friends-by-age.py) -> [friends-by-age-df](./friends-by-age-dataframe.py)
- [word-count](./word-count-better-sorted.py) -> [word-count-df](./word-count-better-sorted-dataframe.py)
- [min-temp](./min-temperatures.py) -> [min-temp-df](min-temperatures-dataframe.py)
- [customer-order](./total-spent-by-customer-sorted.py) -> [customer-order-df](./total-spent-by-customer-sorted-dataframe.py)


## DataFrame vs DataSet
- In Spark 2+, a DataFrame is really a DataSet of Row objects
> As mentioned above, in Spark 2.0, 
> DataFrames are just Dataset of Rows in Scala and Java API. 
> These operations are also referred as “untyped transformations” 
> in contrast to “typed transformations” come with strongly typed 
> Scala/Java Datasets.
>
- DataSets is a more scala things
- DataSets can wrap known, typed data too. But this is mostly transparent to you in Python, since Python is untyped
- So, Don't sweat this too much with Python. But in Scala, you'd want to use DataSets whenever possible
    _ Because they are typed, they can be stored more efficiently
    - They can also be optimized at compile time

### DataFrame Tricks (Shell access)
- Spark SQL exposes a JDBC/ODBC server (if you built Spark with Hive support
- start it with `sbin/start-thriftserer.sh`
- Listens on port 100000 by default
- Connect using `bin/beeline -u jdbc:hive2://localhost:10000`
- viola, you have a SQL shell to spark SQL
- you can create new tables, or query existing ones that were cached using hiveCtx.cacheTable("tableName")



### User-defined functions (UDF's)
Pandas UDF 是以一个 Pandas Series 为单位，batch 的大小可以由 spark.sql.execution.arrow.maxRecordsPerBatch 这个参数来控制。这是一个来自官方文档的示例：

```Python
from pyspark.sql.types import InteferTYpe
def square(x):
    return x*x
spark.udf.register("square", square, IntegerType())
df = spark.sql("SELECT square('someNumericField') FROM tableName")
```


---
# Spark Streaming
- data stream = data is arriving continuously in an unbounded sequence
- Source from = Kafka, Apache Flume, Amazon Kinesis or TCP sockets

## Internal working of `Spark SStreaming`
> Discretize Stream (DStream):
>
> Basically, it represents a stream of data divided into small batches built on Spark RDDs.  
> 
> Seamlessly integrate with any other Apache Spark components. Such as Spark MLlib and Spark SQL.

---
# Spark ML

---
# Spark NetworkX

---
# Limitation of Apache Spark Programming
1. No Support for Real-Time Processing (Micro-batch is not __Real__ RT)
2. Problem with Small File 
3. No File Management System
4. Expensive (Keep Data in memory is expensive)
5. Less number of Algorithm
6. Manual Optimization
7. Iterative Processing (Basically, here data iterates in batches. Also, each iteration is scheduled and executed separately.)
8. Latency (Compare to Flink)
9. Window criteria (Spark only support time-based window criteria not record based window criteria.)

---
# Questions

## Q: Spark Context vs Spark Session?
- SparkContext is the main entrance for RDD, SparkSession is the entrance for Spark DataFrame
- SparkContext, SQLContext and HiveContext are old entry point  
- All you have to do is to create a SparkSession that offers support to Hive and sql-like operations.
- [Reference](https://towardsdatascience.com/sparksession-vs-sparkcontext-vs-sqlcontext-vs-hivecontext-741d50c9486a)

```scala
import org.apache.spark.sql.SparkSession
val sparkSession = SparkSession \
    .builder() \
    .appName("myApp") \
    .enableHiveSupport() \
    .getOrCreate()
// Two ways you can access spark context from spark session
val spark_context = sparkSession._sc
val spark_context = sparkSession.sparkContext
# Recommend, using SparkSession to control SparkContext?
spark.sparkContext().textFile(yourFileOrURL)
```
- Example of using SparkSession create SparkContext [link](./word-count-better-sorted-nlp.py)
- Conclusion, use SparkContext() when operate on raw rdd. All others, use SparkSession(). 

## Q: ...

---
# Reference
- [Scala Official Example](https://github.com/apache/spark/blob/master/examples/src/main/python/wordcount.py)
- [PySpark - Quick Guide - Tutorialspoint](https://www.tutorialspoint.com/pyspark/pyspark_quick_guide.htm)
- [PySpark 入门 – IBM Developer](https://developer.ibm.com/zh/technologies/analytics/tutorials/getting-started-with-pyspark/)
- [PySpark 原理解析 | 技术大神分享 - Nativex](https://www.nativex.com/cn/blog/2019-12-27-2/)
- [Spark Tutorial - Learn Spark Programming - DataFlair](https://data-flair.training/blogs/spark-tutorial/)
- [Big Data Analytics by  Venkat Ankam](https://www.amazon.com/Big-Data-Analytics-Venkat-Ankam/dp/1785884697)
- [Spark RDD Document](http://spark.apache.org/docs/latest/api/python/pyspark.sql.html?highlight=udf#pyspark.sql.SparkSession.sparkContext))


> updated to bear

