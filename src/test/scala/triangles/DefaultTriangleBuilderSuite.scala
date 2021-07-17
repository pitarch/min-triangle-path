package triangles

import org.scalatest.Inside
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class DefaultTriangleBuilderSuite extends AnyFunSuite with Matchers with Inside {

  implicit val _parser: Parser = Parser.default
  val builder: DefaultTriangleBuilder = new DefaultTriangleBuilder

  test("Empty encoded tree") {
    val content = ""
    inside(builder.build(content)) {
      case Right(tree) => tree shouldBe EmptyTriangle
    }
  }

  test("only a root tree") {
    val content = "1"
    inside(builder.build(content)) {
      case Right(tree) =>
        tree shouldBe a[Leaf]
        tree.value shouldBe 1
        tree.weight shouldBe 1
    }
  }

  test("tree with two levels") {
    val content =
      """
        |7
        |6 3
        |""".stripMargin
    inside(builder.build(content)) {
      case Right(tree) =>
        tree shouldBe a[Branch]
        tree.value shouldBe 7
        tree.weight shouldBe 10
    }
  }


  test("tree with three levels") {
    val content =
      """
        |1
        |10 20
        |100 200 300
        |""".stripMargin
    inside(builder.build(content)) {
      case Right(tree) =>
        tree shouldBe a[Branch]
        tree.value shouldBe 1
        tree.weight shouldBe 111
    }
  }

  test("Error when no one-root tree") {
    val content =
      """
        |10 20
        |100 200 300
        |""".stripMargin
    inside(builder.build(content)) {
      case Left(error) => error shouldBe an[IllegalStateException]
    }
  }
}
