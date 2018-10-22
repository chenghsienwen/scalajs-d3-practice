package example

import org.singlespaced.d3js.d3

import scala.scalajs.js
import js.Dynamic.{global => g}
import scala.scalajs.js.Dictionary
import js.JSConverters._
import org.singlespaced.d3js.Ops._

//reference:https://www.oxxostudio.tw/articles/201509/svg-d3-20-bar-chart-tainan-dengue.html
object ScalaJSExample7 extends js.JSApp {
  case class Item(index: String,date: String,block: String,section: String,road: String,lattitude:Double,longtitude: Double)
  case class countByMetrics(key: String, count: Long)
  def main(): Unit = {
    d3.csv("src/main/scala/resources/tainan-dengue.csv", (error: Any, raw: js.Array[Dictionary[String]]) => {

      val data = raw.map{ i =>
        val _id = i("index")
        val index = _id.length < 2 match {
          case true => s"0${_id}"
          case false => _id
        }
        Item(index, i("date"), i("block"), i("section"), i("road"), i("lattitude").toDouble, i("longtitude").toDouble)
      }

      g.console.log(data)
      val countByDate = data.groupBy(_.date).map(i => countByMetrics(i._1, i._2.length)).toJSArray
      var s = d3.select("body").append("svg")
      .attr(
        "width", 800)
        .attr("height", data.length*15+20
      ).style("border", "1px solid #000")
      var rect = s.append("g")
        .attr(
          "id", "rect"
        )
      var num = s.append("g")
        .attr(
          "id", "num"
        )
      var date = s.append("g")
        .attr(
          "id", "date"
        )

      rect.selectAll("rect")
      .data(countByDate)
        .enter()
        .append("rect")
      .attr("width", (d: countByMetrics) => d.count)
        .attr("height", 10)
        .attr("fill", (d: countByMetrics) => {
          if(d.count>300){
             "#c00"
          }else if(d.count>200 && d.count<=300){
             "#f90"
          }else if(d.count>100 && d.count<=200){
             "#aa0"
          }else if(d.count>50 && d.count<=100){
             "#ac0"
          }else{
             "#6c0"
          }
        })
        .attr("x", 100)
        .attr("y", (d: countByMetrics) => {
          d.count*15
        })

      num.selectAll("text")
      .data(countByDate)
        .enter()
        .append("text")
      .attr(
        "fill","#000")
        .attr("x", (d: countByMetrics) => {
           d.count+105
        })
        .attr("y", (d: countByMetrics, i: Int) =>{
           i * 15 + 10
        })
      .text((d: countByMetrics, i:Int) =>{
         d.key
      }).style(
        "font-size", "12px"
      )
      date.selectAll("text")
      .data(countByDate)
        .enter()
        .append("text")
      .attr(
        "fill","#000")
        .attr(  "text-anchor", "end")
        .attr("x", 90)
        .attr("y", (d:countByMetrics, i:Int) => {
          g.console.log(d.key, d.count)
          (i * 15 + 10)
        }
      ).text((d:countByMetrics, i:Int) =>{
         d.key
      }).style(
        "font-size","12px"
      )
      () 
    })
  }
}
