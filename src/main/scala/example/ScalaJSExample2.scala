package example

import org.singlespaced.d3js.Ops._
import org.singlespaced.d3js.d3

import scala.scalajs.js
//reference:http://blog.infographics.tw/2015/03/d3js-the-introduction/
object ScalaJSExample2 extends js.JSApp {
  def main(): Unit = {
    val death_rate = js.Array(js.Array("", 0),
                              js.Array("越南", 24.26),
                              js.Array("阿魯巴", 17.48),
                              js.Array("關島", 10.01),
                              js.Array("澳門", 5.84))

    val span = d3.select("body").select("h3").select("span")
    span.text("hello world").style("font-size", "24px")
    val div = d3.select("body").selectAll("div")

    val div_data_bind = d3.select("body").selectAll("div").data(death_rate)
    val div_set = div_data_bind.enter().append("div") /*為「沒有物件可配對的資料」建立標籤 */
    div_data_bind.exit().remove() /* 刪除「沒有資料可配對的物件」*/
    div_set.text((d: js.Array[Any], i: Int) => s"$i :${d(0)} ${d(1)}")

    div_set.style("height", "20px")
    div_set.style("background", "red")
    div_set.style("margin", "5px")
    div_set.style("width", (d: js.Array[Any], i: Int) => {
      s"${d(1).toString.toFloat * 10}px"
    })

    var data = js.Array(1, 2, 3, 4, 5)

    d3.select("body")
      .selectAll("div")
      .data(data)
      .enter()
      .append("div")
      .text((d: Int, i: Int) => s"$i :${d}}")
  }
}
