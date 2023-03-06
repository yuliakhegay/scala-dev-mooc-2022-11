package module1

import scala.util.Random

class BallsExperiment {

  val listOfBalls: List[Int] = List(0, 0, 0, 1, 1, 1)
  def isWhite(): Boolean = {
    val rand = new scala.util.Random
    if (listOfBalls.apply(rand.nextInt(6)) == 1) true else false
  }

  def isFirstBlackSecondWhite(): (Boolean, Boolean) = {
    val rand = new scala.util.Random
    (listOfBalls.apply(rand.nextInt(6)), listOfBalls.apply(rand.nextInt(6)))

    //    if (listOfBalls.apply(rand.nextInt(6)) == 1) true else false
  }

}

object BallsTest {
  def main(args: Array[String]): Unit = {
    val count = 10000
    val listOfExperiments: List[BallsExperiment] = for (i <- List.range(1, count + 1)) yield new BallsExperiment  // список экспериментов и что было в рез-те получено -- 0 или 1
    val resultsOfExperiments: List[Boolean] = listOfExperiments.map(_.isWhite())
    val countOfPositiveExperiments: Float = resultsOfExperiments.count(_ == true)
    println(countOfPositiveExperiments / count)
  }
}