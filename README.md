
fork from https://github.com/spaced/scala-js-d3-example-app

# Barebone application written in Scala.js using d3 facade
[![Scala.js](http://scala-js.org/assets/badges/scalajs-0.6.6.svg)](http://scala-js.org)

![](https://github.com/spaced/scala-js-d3/blob/master/site/d3demo.png)

This is a barebone example of an application written in
[Scala.js](https://www.scala-js.org/) using [d3 scala js facade](https://github.com/spaced/scala-js-d3).

## Get started

To get started, open `sbt` in this example project, and execute the task
`fastOptJS`. This creates the file `target/scala-2.11/example-fastopt.js`.
You can now open `index.html` in your favorite Web browser!

During development, it is useful to use `~fastOptJS` in sbt, so that each
time you save a source file, a compilation of the project is triggered.
Hence only a refresh of your Web page is needed to see the effects of your
changes.

## The fully optimized version

For ultimate code size reduction, use `fullOptJS`. This will take several
seconds to execute, so typically you only use this for the final, production
version of your application.

## Testing
For Tests you need phantomjs because d3js uses dom features not supported by Rhino
If phantomjs installed, the tests can be run with:


    > set scalaJSStage in Global := FastOptStage
    > test
