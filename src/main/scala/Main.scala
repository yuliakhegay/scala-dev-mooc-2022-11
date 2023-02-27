import module1.threads.{Thread1, ToyFuture, getRatesLocation1, getRatesLocation2, getRatesLocation3, getRatesLocation4, getRatesLocation5, getRatesLocation6, printRunningTime}
import module1.{functions, future, promise, type_system}
import module2.implicits.{implicit_conversions, implicit_scopes}
import module3.functional_effects
import functional_effects.functionalProgram.declarativeEncoding
import functional_effects.functionalProgram.executableEncoding
import module1.list.{List, _}

import scala.util.Try

object Main{


  def main(args: Array[String]): Unit = {

//    val t1 = new Thread1
//    val t2 = new Thread1
//    t1.start()
//    t1.join()
//    t2.start()

//    def rates = {
//
//      getRatesLocation5.onComplete{ i1 =>
//        getRatesLocation6.onComplete{ i2 =>
//          println(i1 + i2)
//        }
//      }
//
//      for{
//        i1 <- getRatesLocation5
//        i2 <- getRatesLocation6
//
//      } yield i1 + i2
//
//    }
//
//    printRunningTime(rates)
    import scala.concurrent.ExecutionContext.Implicits.global

//    def rates = for{
//      r1 <- future.getRatesLocation1
//      r2 <- future.getRatesLocation2
//    } yield  r1 + r2
//
//    def rates2 = future.getRatesLocation1.flatMap(
//      r1 => future.getRatesLocation2.map(r2 => r1 + r2)
//    )

    //future.printRunningTime(rates)

//    future.f8.foreach{
//      println(_)
//    }

//    println(promise.p1.isCompleted)
//    println(promise.f1.isCompleted)
//    promise.p1.complete(Try(10))
//    println(promise.p1.isCompleted)
//    println(promise.f1.isCompleted)
//    promise.f1.foreach(println(_))
//
//    Thread.sleep(4000)


    //implicit_conversions.res
    // implicit_scopes.result
  //  println(s"Hello, from ${Thread.currentThread().getName}")

//    val r: executableEncoding.Console[Unit] = executableEncoding.p3
//    val r2: declarativeEncoding.Console[Unit] = declarativeEncoding.p2
//    declarativeEncoding.interpret(r2)

    val l1 = List(1, 2, 3)
    val l2 = List("one", "two", "three")
//    l1.mkString("!") // 1!2!3
    val res1 = List.shoutString(l2, ":") // one:two:three
    res1


  }

}