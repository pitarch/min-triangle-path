package triangles


/**
 * A triangle builder from its encoded representation
 */
trait TriangleBuilder {

  def build(encodedTriangle: String): Either[Throwable, Triangle]
}

/**
 * A default triangle builder from its encoded representation.
 *
 * It uses an implicit parser
 *
 * @param parser parser
 */
class DefaultTriangleBuilder(implicit parser: Parser) extends TriangleBuilder {

  /**
   * build an encoded triangle into its tree-like representation
   *
   * TODO: malformed triangle should return error. For instance: when there are more children nodes than parents. For now
   * let's only implement the happy path.
   *
   * @param encodedTriangle
   * @return
   */
  def build(encodedTriangle: String): Either[Throwable, Triangle] = {
    parser.parse(encodedTriangle) map buildTopDown flatMap {
      case Nil => Right(EmptyTriangle)
      case root +: Nil => Right(root)
      case _ => Left(new IllegalStateException("Multiple roots"))
    }
  }

  private def buildTopDown(matrix: Seq[Seq[Int]]): Seq[Triangle] = {

    matrix.reverse match {
      case leafValues +: tailValues =>
        val leaves: Seq[Triangle] = leafValues.map(Leaf)
        tailValues.foldLeft(leaves)(bindParentNodesTheirChildren)
      case leafValues +: Nil => leafValues.map(Leaf)
      case Nil => Seq.empty
    }
  }

  private def bindParentNodesTheirChildren(children: Seq[Triangle], parents: Seq[Int]): Seq[Triangle] = {
    val pairs = children zip children.tail
    val result = parents zip pairs map { case (value, (left, right)) => Branch(value, left, right) }
    result
  }
}


object TriangleBuilder {

  def default(implicit parser: Parser) = new DefaultTriangleBuilder
}
