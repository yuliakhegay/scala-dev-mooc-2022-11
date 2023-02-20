package module3

import module3.emailService.EmailService
import module3.userDAO.UserDAO
import module3.userService.{UserID, UserService}
import zio.console.Console
import zio.{ExitCode, Has, ULayer, URIO, ZIO, ZLayer}

object App {
  def main(args: Array[String]): Unit = {

    zio.Runtime.default.unsafeRun(zioConcurrency.printEffectRunningTime(zioConcurrency.p3))

   // toyModel.echo.run()

  }
}

object ZioApp extends zio.App{


  val app: ZIO[UserService with EmailService with Console, Throwable, Unit] =
    UserService.notifyUser(UserID(10))



  val env: ULayer[UserService with EmailService] =
    UserDAO.live >>> UserService.live ++ EmailService.live

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] =
    app.provideSomeLayer[Console](env).exitCode
}