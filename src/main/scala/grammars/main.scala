package grammars

import scala.util.Failure
import scala.util.Success



object Main {
  def main(args: Array[String]) = {
    println(unsolicitedAdvice.getOrElse("*continuous farting noises*"))
  }

  def unsolicitedAdvice: Option[String] = {
    wikihow.randomArticle match {
      case Success(article) => Asshole(
        TerminalAction(wikihow.topic(article).getOrElse("")),
        TerminalAction(wikihow.advice(article).getOrElse(""))
      ).randomText
      case Failure(_) => None
    }

  }
}
