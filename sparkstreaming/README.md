https://blog.csdn.net/jenrey/article/details/80287113

python写法（需要用指令去提交）
```
#coding:utf-8
from pyspark import SparkConf, SparkContext
from pyspark.streaming import StreamingContext
from pyspark.streaming.kafka import KafkaUtils

#提交指令 bash /usr/local/spark-2.3.0-bin-hadoop2.7/bin/spark-submit   --master "spark://xxx:7077" --jars ~/PycharmProjects/byrobot/spark-streaming-kafka-0-8-assembly_2.11-2.3.0.jar ~/PycharmProjects/byrobot/kafkastreaming.py

sc = SparkContext(master="spark://xxx:7077", appName="KafkaWordCount")
sc.setLogLevel("ERROR")
# sc.setLocalProperty("spark.driver.extraClasspath", "/Users/peilongcheng/PycharmProjects/byrobot/*")
# 处理时间间隔为2s
ssc = StreamingContext(sc, 2)
kafkaParams={"metadata.broker.list": "172.21.10.53:6667"}
# 打开一个TCP socket 地址 和 端口号
topic = ['CustomerCountry']  # 要列举出分区
groupId = "customer_summer"

lines = KafkaUtils.createDirectStream(ssc=ssc, kafkaParams=kafkaParams, topics=topic).pprint()
ssc.start()
ssc.awaitTermination()
```
