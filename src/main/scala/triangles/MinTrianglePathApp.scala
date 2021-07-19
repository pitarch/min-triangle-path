package triangles

import cats.Show
import cats.effect.std.{Console => Con}
import cats.effect.{ExitCode, IO, IOApp}

object MinTrianglePathApp extends IOApp {

  implicit val _miniConsole: MiniConsole[IO] = new MiniConsole[IO] {
    override def println[A](a: A)(implicit S: Show[A]): IO[Unit] = Con[IO].println(a)

    override def errorln[A](a: A)(implicit S: Show[A]): IO[Unit] = Con[IO].errorln(a)
  }

  override def run(args: List[String]): IO[ExitCode] = {

    val encodedTriangle = IO.delay(Iterator.continually(Console.in.readLine()).takeWhile(_ != null).mkString("\n"))
    ProgramF.program[IO](encodedTriangle)
  }
}