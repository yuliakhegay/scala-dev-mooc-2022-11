package collections

import collections.task_collections.{Auto, capitalizeIgnoringASCII, filterAllLeftDealerAutoWithoutRight, intersectionAuto, numbersToNumericString}
import org.scalatest.flatspec.AnyFlatSpec

class check_collections_task extends AnyFlatSpec {

  "check capitalizeIgnoringASCII" should "ok" in  {
    assert(capitalizeIgnoringASCII(List("Lorem", "ipsum", "dolor", "sit", "amet")) == List("Lorem", "IPSUM", "DOLOR", "SIT", "AMET"))
    println("Test 1 for capitalizeIgnoringASCII successfully passed")

    assert(capitalizeIgnoringASCII(List("Оказывается", ",", "ЗвУк", "КЛАВИШЬ")) === List("Оказывается", ",", "звук", "клавишь"))
    println("Test 1 for capitalizeIgnoringASCII successfully passed")
  }

  "check numbersToNumericString" should "ok" in {
    val text = "Hello. I am 9 years old"
    val transformText = "Hello. I am nine years old"

    assert(numbersToNumericString(text) === transformText)
    println("Test 1 for numbersToNumericString successfully passed")

    assert(numbersToNumericString("") === "")
    println("Test 2 for numbersToNumericString successfully passed")

    assert(numbersToNumericString("4") === "four")
    println("Test 3 for numbersToNumericString successfully passed")
  }

  "check intersectionAuto" should "ok" in {
    val dealerOne = Vector(Auto("BMW", "i3"), Auto("Mazda", "X5"))
    val dealerTwo = Seq(Auto("BMW", "i3"), Auto("Mazda", "X5"))
    assert(intersectionAuto(dealerOne, dealerTwo) === Set(Auto("BMW", "i3"), Auto("Mazda", "X5")))
    println("Test for intersectionAuto successfully passed")
  }

  "check filterAllLeftDealerAutoWithoutRight" should "ok" in {
    val dealerOne = Vector(Auto("BMW", "i3"), Auto("Mazda", "X5"))
    val dealerTwo = Seq(Auto("BMW", "i3"), Auto("Mazda", "X5"))
    assert(filterAllLeftDealerAutoWithoutRight(dealerOne, dealerTwo) === Set.empty)
    println("Test for filterAllLeftDealerAutoWithoutRight successfully passed")

    val dealerOneSecond = Vector(Auto("BMW", "i3"), Auto("Mazda", "X5"))
    val dealerTwoSecond = Seq(Auto("BMW", "i3"))
    assert(filterAllLeftDealerAutoWithoutRight(dealerOneSecond, dealerTwoSecond) === Set(Auto("Mazda", "X5")))
    println("Test for filterAllLeftDealerAutoWithoutRight successfully passed")
  }

}
