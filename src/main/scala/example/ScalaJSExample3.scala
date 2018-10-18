package example

import org.singlespaced.d3js.Ops._
import org.singlespaced.d3js.d3

import scala.scalajs.js
import js.Dynamic.{global => g}
//reference:https://www.oxxostudio.tw/articles/201411/svg-d3-01-data.html
object ScalaJSExample3 extends js.JSApp {
  def main(): Unit = {
    var data = js.Array(0, 38,69,72,42,58,87)

    d3.select("body")
      .selectAll("div")
      .data(data)
      .enter()
      .append("div")
      .text((d: Int, i: Int) => s"$i :${d}}")
      .style("color", (d: Int) => if (d < 60) "red")
      .style("width", (d: Int) => s"${d*10}px")
      .style("margin", "2px")
      .style("background", "#aaa")

    g.console.log(s"min:${d3.min(data.map(i => i.toDouble))}") //最小值
    g.console.log(s"max:${d3.max(data.map(i => i.toDouble))}")
    g.console.log(s"extent:${d3.extent(data.map(i => i.toDouble))}")
    g.console.log(s"mean:${d3.mean(data.map(i => i.toDouble))}")
    g.console.log(s"shuffle:${d3.shuffle(data.map(i => i.toDouble), 0 , 0)}")
  }
}
