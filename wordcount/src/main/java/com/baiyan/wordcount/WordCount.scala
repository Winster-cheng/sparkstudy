import org.apache.spark.{SparkConf, SparkContext}


object WordCount {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
    conf.setAppName("wordcount")
    conf.setMaster("local")
    val sc = new SparkContext(conf)
    val line = sc.textFile("wordcount/resources/wordcount.txt")
    line.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_+_).saveAsTextFile("wordcount/resources/wordcount_result.txt")
    sc.stop()
  }
}
