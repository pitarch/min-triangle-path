package triangles

import scala.util.Try

trait Parser {

  def parse(encoded: String): Either[Throwable, Seq[Seq[Int]]]
}


object Parser {

  /**
   * Parse an encoded triangle tree into a matrix.
   *
   * Each level of the tree is separated by the system line separator (see [[System.lineSeparator]])
   *
   * @throws NumberFormatException in case a value of some triangle node is not an integer
   * @param encoded an encoded triangle
   * @return a matrix representation of the triangle tree.
   */
  def default: Parser = (encoded: String) => Try(encoded
    .split(System.lineSeparator())
    .filter(_.nonEmpty)
    .toIndexedSeq
    .map(_.split("\\s+").map(_.toInt).toIndexedSeq))
    .toEither
}