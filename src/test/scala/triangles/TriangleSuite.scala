package triangles

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class TriangleSuite extends AnyFlatSpec with Matchers {

  "A leaf's weight" should "be its value" in {
    val leaf = Leaf(3)
    leaf.weight shouldBe leaf.value
  }

  "A branch's weight" should "be the lowest weight of its subbranches plus its own weight" in {
    val tree1 = Branch(7, Leaf(3), Leaf(6))
    val tree2 = Branch(1, Leaf(3), Leaf(5))

    tree1.weight shouldBe 10
    tree2.weight shouldBe 4
    Branch(100, tree1, tree2).weight shouldBe (100 + tree2.weight)
    Branch(100, tree2, tree1).weight shouldBe (100 + tree2.weight)
  }

  "An empty triangle" should "have its value to zero" in {
    EmptyTriangle.value shouldBe 0
  }

  it should "have its weight to zero" in {
    EmptyTriangle.weight shouldBe 0
  }
}
