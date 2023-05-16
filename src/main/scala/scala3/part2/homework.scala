package part2.homework
import scala.annotation.targetName


object intExtensions:

  extension (x: Int)
    @targetName("concat")
    def add (y: Int): String = x.toString.concat(y.toString)

  def main(args: Array[String]): Unit =
    println(56 add 3)

end intExtensions


object Completions:

  enum CompletionArg:
    case ShowItAsString(a: String)
    case ShowItAsInt(a: Int)
    case ShowItAsFloat(a: Float)


  object CompletionArg:

    given fromString: Conversion[String, CompletionArg] = ShowItAsString(_)
    given fromInt: Conversion[Int, CompletionArg] = ShowItAsInt(_)
    given fromFloat: Conversion[Float, CompletionArg] = ShowItAsFloat(_)

  end CompletionArg
  import CompletionArg.*

  def complete[T](arg: CompletionArg) = arg match
    case ShowItAsString(_) => arg.toString
    case ShowItAsInt(_) => arg.toString.toInt
    case ShowItAsFloat(_) => arg.toString.toFloat

end Completions


import java.time.LocalDate

object myOpaqueType:

  opaque type Year = Int

  object Year:
    def apply(n: Int): Year = n

    def safe(n: Int): Option[Year] =
      if n <= LocalDate.now.getYear then Some(n) else None

  end Year

  extension (n: Year)
    def howLongAgo: Int = LocalDate.now.getYear - n
    def beforeWorldWarTwo: Boolean = n < 1939

  def main(args: Array[String]): Unit =
    val myYear: Year = Year(2008)
    val fakeYear: Int = 1380

    println(myYear.howLongAgo)
    println(myYear.beforeWorldWarTwo)
    println(fakeYear.howLongAgo)
    println(fakeYear.beforeWorldWarTwo)

end myOpaqueType
