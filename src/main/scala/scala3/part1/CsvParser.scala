package scala3
package part1.CsvParser

class MonadParser[T, Src](private val p: Src => (T, Src)):
  def flatMap[M](f: T => MonadParser[M, Src]): MonadParser[M, Src] =
    MonadParser { src =>
      val (word, rest) = p(src)
      val mn = f(word)
      val res = mn.p(rest)

      res
    }

  def map[M](f: T => M): MonadParser[M, Src] =
    MonadParser { src =>
      val (word, rest) = p(src)
      (f(word), rest)
    }

  def parse(src: Src): T = p(src)._1


object MonadParser:
  def apply[T, Src](f: Src => (T, Src)) =
    new MonadParser[T, Src](f)


trait FieldConversion[A, B]:
  def convert(x: A): B


given intFieldConversion: FieldConversion[String, Int] with
  def convert(x: String): Int = x.toInt

given floatFieldConversion: FieldConversion[String, Float] with
  def convert(x: String): Float = x.toFloat

given booleanFieldConversion: FieldConversion[String, Boolean] with
  def convert(x: String): Boolean = x.toBoolean


object TestExecution:

  def parse[String, B](x: String)(using fieldConversion: FieldConversion[String, B]): B =
    fieldConversion.convert(x)


  def main(args: Array[String]): Unit =

    def StringField =
      MonadParser[String, String] { str =>
        val idx = str.indexOf(";")
        if (idx > -1)
          (str.substring(0, idx), str.substring(idx + 1))
        else
          (str, "")
      }

    def IntField: MonadParser[Int, String] =  StringField.map(parse)
    def FloatField: MonadParser[Float, String] = StringField.map(parse)
    def BooleanField: MonadParser[Boolean, String] = StringField.map(parse)


    case class Car(year: Int, mark: String, model: String, comment: String, price: Float)

    val str = "1997;Ford;E350;ac, abs, moon;3000\n1996; Jeep; Grand Cherokee; MUST SELL! air, moon roof, loaded; 4799"
    given delimiter: String = "\n"

    def parseCsv(srcText: String)(using splitter: String): Array[Car] =
      val parser =
        for
          year <- IntField
          mark <- StringField
          model <- StringField
          comment <- StringField
          price <- FloatField
        yield Car(year, mark, model, comment, price)

      srcText.split(splitter).map(parser.parse)

    println(parseCsv(str).map(x => s"${x.model}, ${x.mark}, ${x.year}").mkString(";"))
