package triangles

import cats.MonadError
import cats.effect.ExitCode

object ProgramF {

  def program[F[_] : MiniConsole](encodedTriangle: F[String])(implicit M: MonadError[F, Throwable]): F[ExitCode] = {
    val miniConsole = implicitly[MiniConsole[F]]
    val findMinimalPath: String => Either[Throwable, Seq[Int]] = TriangleBuilder
      .default
      .build(_)
      .map(DefaultTrianglePathResolver.forMinimalPath.resolve)

    // find the minimal path
    val step1: F[String] => F[Either[Throwable, Seq[Int]]] = M.lift(findMinimalPath)

    // generate and print the report
    val step2: F[Either[Throwable, Seq[Int]]] => F[Either[Throwable, Seq[Int]]] = M.flatTap(_: F[Either[Throwable, Seq[Int]]]) {
      case Left(error) => miniConsole.errorln(error)
      case Right(path) =>
        val pathAsString = path.map(_.toString).mkString(" + ")
        val report = s"Minimal path is: $pathAsString = ${path.sum}"
        miniConsole.println(report)
    }

    // generate the exit code of the program
    val step3 = M.map(_: F[Either[Throwable, Seq[Int]]]) {
      case Right(_) => ExitCode.Success
      case Left(_) => ExitCode.Error
    }

    (step1 andThen step2 andThen step3) (encodedTriangle)
  }
}

