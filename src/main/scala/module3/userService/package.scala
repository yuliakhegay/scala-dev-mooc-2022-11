package module3

import zio.{Has, RIO}
import zio.macros.accessible
import emailService.{Email, EmailAddress, EmailService, Html}
import module3.userDAO.UserDAO
import zio.console
import zio.ZLayer
import zio.ZIO
import zio.console.Console

package object userService {

  /**
   * Реализовать сервис с одним методом
   * notifyUser, принимает id пользователя в качестве аргумента и шлет ему уведомление
   * при реализации использовать UserDAO и EmailService
   */

   type UserService = Has[UserService.Service]

  @accessible
   object UserService{
     trait Service{
       def notifyUser(id: UserID): ZIO[EmailService with Console, Throwable, Unit]
     }
     class ServiceImpl(userDAO: UserDAO.Service) extends Service{
       def notifyUser(id: UserID): ZIO[EmailService with Console, Throwable, Unit] =
         for{
         user <- userDAO.findBy(id).some.orElseFail(new Throwable(s"User not found by $id"))
         email = Email(user.email, Html("Hello here"))
//         emailService <- ZIO.environment[EmailService].map(_.get)
//         _ <- ZIO.accessM[EmailService with Console](_.get.sendMail(email))
         _ <- EmailService.sendMail(email)

       } yield ()
     }
     val live: ZLayer[UserDAO, Nothing, UserService] = ZLayer.fromService[UserDAO.Service, UserService.Service]{ dao =>
       new ServiceImpl(dao)
     }

   }



}
