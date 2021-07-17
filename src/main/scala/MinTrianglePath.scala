import triangles.{DefaultTrianglePathResolver, Parser, TriangleBuilder}

object MinTrianglePath extends App {

  implicit val parser: Parser = Parser.default
  val builder = TriangleBuilder.default
  val pathResolver = DefaultTrianglePathResolver.forMinimalPath
  val encodedTriangle = Iterator.continually(Console.in.readLine()).takeWhile(_ != null).mkString
  val mayBeTriangle = builder.build(encodedTriangle)
  mayBeTriangle
    .map(pathResolver.resolve)
    .foreach { path =>
      val report = path.map(_.toString).mkString(" + ")
      println(s"Minimal path is: $report = ${path.sum}")
    }
}
