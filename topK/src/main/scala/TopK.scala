import org.apache.spark.{SparkConf, SparkContext}
object TopK {
  def main(args: Array[String]): Unit = {
    //创建spark
    val conf = new SparkConf().setMaster("local").setAppName("TopK")
    val sc = new SparkContext(conf)
    //读取数据
    val dataRDD=sc.textFile("topK/src/main/scala/wordcount.txt").flatMap(_.split(" "))
      .map(x=>(x,1))
      .reduceByKey((a,b)=>(a+b))
    //K,V 值反转，并取回前三位数
    val topRDD=dataRDD.map{
      case(key,value) =>(value,key)}.sortByKey().top(3).foreach(x =>println(x._2+"-->"+x._1))
    sc.stop()
    }
}
