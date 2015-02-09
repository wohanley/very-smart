package object wikihow {

  import org.jsoup.Jsoup
  import org.jsoup.nodes.Document


  def randomArticle: Document =
    Jsoup.connect("http://www.wikihow.com/Special:Randomizer").get()

  def topic(article: Document): String = "your butt"

  def advice(article: Document): String = "wipe your ass more"
}
