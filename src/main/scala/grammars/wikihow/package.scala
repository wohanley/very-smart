package object wikihow {

  import org.jsoup.Jsoup
  import org.jsoup.nodes.Document
  import org.jsoup.nodes.Element


  def randomArticle: Document =
    Jsoup.connect("http://www.wikihow.com/Special:Randomizer").get()

  def topic(article: Document): Option[String] =
    article.select("h1.firstHeading a").first match {
      case element: Element => Some(element.text)
      case _ => None
    }

  def advice(article: Document): Option[String] =
    grammars.takeRandom(article.select("b.whb").toArray(Array.empty[Element]))
      .map(element => element.text)
}
