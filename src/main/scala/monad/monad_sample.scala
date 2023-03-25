package scala

object monadPackage {

  class MonadParser[T, Src](private val p: Src => (T, Src)) {

    def flatMap[M](f: T => MonadParser[M, Src]): MonadParser[M, Src] =
      MonadParser {src =>
        val (word, rest) = p(src)
        val mn = f(word)
        mn.p(rest)
      }

    def map[M](f: T => M): MonadParser[M, Src] =
      MonadParser {src =>
        val (word, rest) = p(src)
        (f(word), rest)
      }

    def parse(src: Src): T = p(src)._1
  }

  object MonadParser {
    def apply[T, Src](f: Src => (T, Src)) = new MonadParser[T, Src](f)
  }


  case class TestClass(field1: Int, field2: String, field3: Boolean)

  def main(args: Array[String]): Unit = {
    val str = "1,test1,true\n2,test2,false\n3,test3,true"

    def stringField: MonadParser[String, String] = MonadParser[String, String] {
      str =>
        val idx = str.indexOf(",")
        if (idx != -1) (str.substring(0, idx), str.substring(idx + 1))
        else (str, "")
    }

    def intField: MonadParser[Int, String] = stringField.map(_.toInt)

    def booleanField: MonadParser[Boolean, String] = stringField.map(_.toBoolean)

    val parser = for {
      field1 <- intField
      field2 <- stringField
      field3 <- booleanField
    } yield TestClass(field1, field2, field3)

    val res = str.split("\n").map(parser.parse)
    res.foreach(a => println(a))

  }

}

