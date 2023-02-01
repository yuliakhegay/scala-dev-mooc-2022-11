package module3

import zio.{ExitCode, URIO}

object App {
  def main(args: Array[String]): Unit = {

//    zio.Runtime.default.unsafeRun()

    toyModel.echo.run()

  }
}

object ZioApp extends zio.App{
  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = ???
}