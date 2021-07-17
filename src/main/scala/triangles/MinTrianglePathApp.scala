package triangles

object MinTrianglePathApp extends App {

  val encodedTriangle: EncodedTriangle = Iterator.continually(Console.in.readLine()).takeWhile(_ != null).mkString("\n")
  program(encodedTriangle) foreach println

  def program(encodedTriangle: EncodedTriangle): Either[Throwable, Report] = {
    implicit val parser: Parser = Parser.default
    val builder = TriangleBuilder.default
    val pathResolver = DefaultTrianglePathResolver.forMinimalPath

    val mayBeTriangle = builder.build(encodedTriangle)
    mayBeTriangle
      .map(pathResolver.resolve)
      .map { path =>
        val report = path.map(_.toString).mkString(" + ")
        s"Minimal path is: $report = ${path.sum}"
      }
  }
}
