package web

import grammars.Asshole
import grammars.TerminalAction
import scala.util.Success
import scala.util.Failure


object Worker {

  def main(args: Array[String]) {
    tweetRegularly()
  }

  private def tweetRegularly() = {
    while (true) {
      web.tweet(unsolicitedAdvice.getOrElse("*continuous farting noises*"))
      Thread.sleep(90 * 60 * 1000)
    }
  }

  private def unsolicitedAdvice: Option[String] = {
    wikihow.randomArticle match {
      case Success(article) => Asshole(
        TerminalAction(wikihow.topic(article).getOrElse("")),
        TerminalAction(wikihow.advice(article).getOrElse(""))
      ).randomText
      case Failure(_) => None
    }
  }
}
