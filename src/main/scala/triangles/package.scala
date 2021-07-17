package object triangles {

  trait Triangle {

    def value: Int

    def weight: Int
  }

  case class Branch(value: Int, left: Triangle, right: Triangle) extends Triangle {
    override lazy val weight: Int = value + scala.math.min(left.weight, right.weight)
  }

  case class Leaf(value: Int) extends Triangle {
    override def weight: Int = value
  }

  object EmptyTriangle extends Triangle {
    override def value: Int = 0

    override def weight: Int = 0
  }
}
