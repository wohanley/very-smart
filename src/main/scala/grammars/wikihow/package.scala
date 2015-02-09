package object wikihow {

  import org.jsoup.Jsoup
  import org.jsoup.nodes.Document


  def randomArticle: Document = Jsoup.connect("").get()

  def topic(article: Document): String = "your butt"

  def advice(article: Document): String = "wipe your ass more"
}
