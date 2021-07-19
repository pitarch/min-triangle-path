import cats.Show

package object triangles {

  type Report = String
  type EncodedTriangle = String

  trait Triangle {

    def value: Int

    def weight: Int
  }

  case class Branch(value: Int, left: Triangle, right: Triangle) extends Triangle {
    override val weight: Int = value + scala.math.min(left.weight, right.weight)
  }

  case class Leaf(value: Int) extends Triangle {
    override val weight: Int = value
  }

  object EmptyTriangle extends Triangle {
    override def value: Int = 0

    override def weight: Int = 0
  }


  trait MiniConsole[F[_]] {
    def println[A](a: A)(implicit S: Show[A] = Show.fromToString[A]): F[Unit]
    def errorln[A](a: A)(implicit S: Show[A] = Show.fromToString[A]): F[Unit]
  }
}
