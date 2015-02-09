package grammars


object Main {
  def main(args: Array[String]) = {
    println(unsolicitedAdvice.randomText)
  }

  def unsolicitedAdvice: Asshole = {
    val article = wikihow.randomArticle
    Asshole(
      TerminalAction(wikihow.topic(article).getOrElse("")),
      TerminalAction(wikihow.advice(article).getOrElse(""))
    )
  }
}
