package triangles

import scala.annotation.tailrec
import scala.collection.mutable

trait TrianglePathResolver {

  def resolve(tree: Triangle): Seq[Int]

}


class DefaultTrianglePathResolver(winnerSelector: (Triangle, Triangle) => Triangle) extends TrianglePathResolver {

  override def resolve(tree: Triangle): Seq[Int] = walkPath(tree).toSeq

  @tailrec
  private def walkPath(tree: Triangle, path: mutable.Stack[Int] = mutable.Stack.empty): mutable.Stack[Int] = {

    tree match {
      case EmptyTriangle => path
      case Leaf(value) => path :+ value
      case Branch(value, left, right) =>
        val winner: Triangle = winnerSelector(left, right)
        walkPath(winner, path :+ value)
    }
  }
}


object DefaultTrianglePathResolver {

  def forMinimalPath = new DefaultTrianglePathResolver(selectMinimalWeight)

  private def selectMinimalWeight(left: Triangle, right: Triangle): Triangle =
    if (left.weight < right.weight) left else right
}