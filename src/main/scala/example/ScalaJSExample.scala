package example

import org.singlespaced.d3js.Ops._
import org.singlespaced.d3js.d3
import org.singlespaced.d3js.histogramModule.Bin

import scala.scalajs.js

object ScalaJSExample extends js.JSApp {

  def main(): Unit = {
    /**
      * Adapted from https://bl.ocks.org/mbostock/3048450
      */

    // Generate a Bates distribution of 10 random variables.
    val batsFun = d3.random.bates(10)
    val values= d3.range(1000).map( _ => batsFun.apply())

    // A formatter for counts.
    val formatCount = d3.format(",.0f")

    case class Margin(top:Int,right:Int,bottom:Int,left:Int)
    val margin = Margin(top=10,right=30,bottom=30,left=30)
    val width = 960 - margin.left - margin.right
    val height = 500 - margin.top - margin.bottom

    val x = d3.scale.linear()
      .domain(js.Array(0, 1))
      .range(js.Array(0, width))

    // Generate a histogram using twenty uniformly-spaced bins.
    val data: js.Array[Bin[Double]] = d3.layout.histogram()
      .bins(x.ticks(20))(values)

    val mm:js.Function2[Bin[Double],Double,Double] = (x: Bin[Double], y: Double) => x.y

    val y = d3.scale.linear()
      .domain(js.Array(0, d3.max(data, mm)))
      .range(js.Array(height, 0))

    val xAxis = d3.svg.axis()
      .scale(x)
      .orient("bottom")

    val svg = d3.select("body").append("svg")
      .attr("width", width + margin.left + margin.right)
      .attr("height", height + margin.top + margin.bottom)
      .append("g")
      .attr("transform", "translate(" + margin.left + "," + margin.top + ")")

    val bar = svg.selectAll(".bar")
      .data(data)
      .enter().append("g")
      .attr("class", "bar")
      .attr("transform", (d:Bin[Double]) => "translate(" + x(d.x) + "," + y(d.y) + ")" )

    bar.append("rect")
      .attr("x", 1)
      .attr("width", x(data(0).dx) - 1)
      .attr("height", (d:Bin[Double]) => height - y(d.y))

    bar.append("text")
      .attr("dy", ".75em")
      .attr("y", 6)
      .attr("x", x(data(0).dx) / 2)
      .attr("text-anchor", "middle")
      .text((d:Bin[Double]) => formatCount(d.y))

    svg.append("g")
      .attr("class", "x axis")
      .attr("transform", "translate(0," + height + ")")
      .call(xAxis);  }

}
