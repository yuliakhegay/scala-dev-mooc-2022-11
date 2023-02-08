package module3

import zio.{ExitCode, URIO, ZIO}

object App {
  def main(args: Array[String]): Unit = {

    zio.Runtime.default.unsafeRun(zioConcurrency.printEffectRunningTime(zioConcurrency.p3))

   // toyModel.echo.run()

  }
}

object ZioApp extends zio.App{
  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] =
    zioRecursion.factorialZ(5).map(r => println(r)).exitCode
}