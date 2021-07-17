package triangles

import org.scalatest.Inside
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class DefaultTrianglePathResolverSuite extends AnyFunSuite with Matchers with Inside {

  val resolver: DefaultTrianglePathResolver = DefaultTrianglePathResolver.forMinimalPath

  test("empty triangle") {
    val triangle = EmptyTriangle
    resolver.resolve(triangle) shouldBe Seq.empty
  }

  test("triangle of one level") {
    val triangle = Leaf(1)
    resolver.resolve(triangle) shouldBe Seq(1)
  }

  test("triangle of two levels (1)") {
    val triangle = Branch(7, Leaf(6), Leaf(3))
    resolver.resolve(triangle) shouldBe Seq(7, 3)
  }


  test("triangle of two levels (2)") {
    val triangle = Branch(7, Leaf(3), Leaf(6))
    resolver.resolve(triangle) shouldBe Seq(7, 3)
  }


  test("triangle of three levels") {
    val left = Branch(6, Leaf(3), Leaf(8))
    val right = Branch(3, Leaf(8), Leaf(5))
    val triangle = Branch(7, left, right)
    resolver.resolve(triangle) shouldBe Seq(7, 3, 5)
  }

  test("triangle of four levels") {
    val node3a = Branch(3, Leaf(11), Leaf(2))
    val node3b = Branch(8, Leaf(2), Leaf(10))
    val node3c = Branch(5, Leaf(10), Leaf(9))
    val node2a = Branch(6, node3a, node3b)
    val node2b = Branch(3, node3b, node3c)
    val root = Branch(7, node2a, node2b)
    resolver.resolve(root) shouldBe Seq(7, 6, 3, 2)
  }
}
