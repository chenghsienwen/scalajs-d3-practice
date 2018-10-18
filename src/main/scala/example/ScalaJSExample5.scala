package example

import org.singlespaced.d3js.d3

import scala.scalajs.js

//reference:https://www.oxxostudio.tw/articles/201411/svg-d3-03-scale-linear.html
object ScalaJSExample5 extends js.JSApp {
  case class Point(x: Double, y: Double)
  def main(): Unit = {
    var data = js.Array(Point(0, 1.89),
                        Point(1, 2.77),
                        Point(2, 0.86),
                        Point(3, 3.45),
                        Point(4, 4.13),
                        Point(5, 3.59),
                        Point(6, 2.33),
                        Point(7, 3.79),
                        Point(8, 2.61),
                        Point(9, 2.15))

    var width = 240
    var height = 120

    var s = d3
      .select("body")
      .append("svg")
      .attr("width", 800)
      .attr("height", 800)

    s.attr("width", width)
      .attr("height", height)
      .style(
        "border",
        "1px solid #000"
      )
    var scaleX = d3.scale.linear()
      .range(js.Array(0,width))
      .domain(js.Array(0,9)).nice()

    var scaleY = d3.scale.linear()
      .range(js.Array(0,height))
      .domain(js.Array(0,5))
    var line = d3.svg
      .line[Point]()
      .x((d: Point, i: Int) => scaleX(d.x))
      .y((d: Point, i: Int) => scaleY(d.y))


    s.append("path")
      .attr("d", line(data))
      .attr("y", 0)
      .attr("stroke", "#000")
      .attr("stroke-width", "5px")
      .attr("fill", "none")
  }
}
