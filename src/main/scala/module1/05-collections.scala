package module1

import scala.util.Random

class BallsExperiment {

  val listOfBalls: List[Int] = Random.shuffle(List(0, 0, 0, 1, 1, 1))

  def pickTwoBalls(): (Boolean, Boolean) = {
    val rand = new scala.util.Random

    val firstBall = rand.nextInt(listOfBalls.length)
    val firstPickResult = listOfBalls(firstBall) == 1

    val secondBall = rand.nextInt(listOfBalls.length - 1)
    val secondPickResult = listOfBalls.patch(firstBall, Nil, 1)(secondBall) == 1

    (firstPickResult, secondPickResult)
  }
}


object BallsTest {
  def main(args: Array[String]): Unit = {
    val count = 100000
    val listOfExperiments: List[BallsExperiment] = List.range(0, count).map(_ => new BallsExperiment)
    val resultsOfExperiments: List[(Boolean, Boolean)] = listOfExperiments.map(_.pickTwoBalls())

    val firstBlackExperiments: List[(Boolean, Boolean)] = resultsOfExperiments.filter(_._1 == false)
    val firstBlackSecondWhiteCount: Float = firstBlackExperiments.count(_._2 == true)

    println(firstBlackSecondWhiteCount / firstBlackExperiments.length)
  }
}
