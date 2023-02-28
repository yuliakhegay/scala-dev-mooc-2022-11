package module3

import zio.UIO

import java.util.concurrent.atomic.AtomicReference
import zio.ZIO
import zio.Chunk
import zio.Schedule

import scala.language.postfixOps
import zio.duration.durationInt
import zio.console
import zio.clock.Clock
import zio.random._
import zio.console._
import zio.duration.Duration
import zio.Promise
import zio.URIO
import sbt.testing.Task

import scala.collection.immutable.AbstractSeq

object zioDS {

  object schedule {

    val eff = ZIO.effect(println("hello"))

    /** 1. Написать эффект, котрый будет выводить в консоль Hello 5 раз
      */

    lazy val schedule1 = Schedule.recurs(5)

    lazy val eff1 = eff.repeat(schedule1)

   
    /** 2. Написать эффект, который будет выводить в консоль Hello 5 раз, раз в секунду
      */
     lazy val schedule2 = Schedule.fixed(1 seconds)
     lazy val eff2 = eff.repeat(schedule1 && schedule1)


    /** Написать эффект, который будет генерить произвольное число от 0 до 10,
      * и повторяться пока число не будет равным 0
      */

    lazy val schedule3 = Schedule.recurWhile[Int]( _ > 0)

    lazy val random = nextIntBetween(0, 11)

    lazy val eff3 = random.repeat(schedule3)

    /** Написать планировщик, который будет выполняться каждую пятницу 12 часов дня
      */

    lazy val schedule5 = Schedule.dayOfWeek(5) && Schedule.hourOfDay(12)
  }

  object ref {

   /**
    *  Счетчик
    * 
    */

    var counter: Int = 0

    val updateCounter: UIO[Int] =
      UIO.foreachPar((1 to 5).toList){_ =>
        ZIO.effectTotal(counter += 1).as(counter)
      }.map(_.last)









    trait Ref[A] {
      def modify[B](f: A => (B, A)): UIO[B]

      def get: UIO[A] = modify(a => (a, a))

      def set(a: A): UIO[Unit] = modify(_ => ((), a))

      def update[B](f: A => A): UIO[Unit] =
        modify(a => ((), f(a)))
    }

    object Ref {
      def make[A](a: A): UIO[Ref[A]] = ZIO.effectTotal{
        new Ref[A] {

          val atomic = new AtomicReference(a)

          override def modify[B](f: A => (B, A)): UIO[B] = ZIO.effectTotal{
            var l = true
            var b: B = null.asInstanceOf[B]
            while(l){
              val current = atomic.get
              val tuple = f(current)
              b = tuple._1
              l = !atomic.compareAndSet(current, tuple._2)
            }
            b
          }
        }
      }
    }

    /**
     * корректный счетчик
     */

    lazy val updateCounterRef = for{
      counter <- Ref.make(0)
      _ <- UIO.foreachPar_(1 to 100)(_ => counter.update(_ + 1))
      res <- counter.get
    } yield res

  }


}
