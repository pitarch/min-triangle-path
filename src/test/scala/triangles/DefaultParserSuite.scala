package triangles

import org.scalatest.Inside
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class DefaultParserSuite extends AnyFunSuite with Matchers with Inside {

  test("Empty encoded tree") {
    inside(Parser.default.parse("")) {
      case Right(triangle) => triangle shouldBe Seq.empty
    }
  }

  test("Encoded tree of 1 level") {
    inside(Parser.default.parse("7")) {
      case Right(triangle) => triangle should contain theSameElementsInOrderAs Seq(Seq(7))
    }
  }

  test("Encoded tree of 2 levels") {
    val content =
      """
        |7
        |3 4
        |""".stripMargin
    inside(Parser.default.parse(content)) {
      case Right(triangle) => triangle should contain theSameElementsInOrderAs Seq(Seq(7), Seq(3, 4))
    }
  }

  test("Encoded tree of 3 levels") {
    val content =
      """
        |1
        |2 3
        |4 5 6
        |""".stripMargin
    inside(Parser.default.parse(content)) {
      case Right(triangle) => triangle should contain theSameElementsInOrderAs Seq(Seq(1), Seq(2, 3), Seq(4, 5, 6))
    }
  }
}
