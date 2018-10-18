package example

import org.singlespaced.d3js.d3

import scala.scalajs.js


//reference:https://www.oxxostudio.tw/articles/201411/svg-d3-02-line.html
//reference:https://github.com/spaced/scala-js-d3/issues/7
object ScalaJSExample4 extends js.JSApp {
  case class Point(x: Int, y: Int)
  def main(): Unit = {
    //try svg

    d3.select("svg")
      .append("circle")
      .attr("cx", 50)
      .attr("cy", 50)
      .attr("r", 30)
      .attr("fill", "#f90")
      .attr("stroke", "#c00")
      .attr("stroke-width", "5px")

    var data =
      js.Array(Point(10, 10), Point(50, 100), Point(60, 50), Point(100, 30))
    var svg = d3
      .select("body")
      .append("svg")
      .attr("width", 800)
      .attr("height", 800)

//    var line1 = d3.svg
//      .line[Point]()
//      .x((d: Point, i: Int) => d.x.toDouble)
//      .y((d: Point, i: Int) => d.y.toDouble)
//      .interpolate("linear-closed")
//    svg
//      .append("path")
//      .attr("d", line1(data))
//      .attr("y", 0)
//      .attr("stroke", "#000")
//      .attr("stroke-width", "5px")
//      .attr("fill", "none")

    var line2 = d3.svg
      .line[Point]()
      .x((d: Point, i: Int) => d.x.toDouble)
      .y((d: Point, i: Int) => d.y.toDouble)
      .interpolate("step")
    svg
      .append("path")
      .attr("d", line2(data))
      .attr("y", 0)
      .attr("stroke", "#000")
      .attr("stroke-width", "5px")
      .attr("fill", "none")


  }
}
