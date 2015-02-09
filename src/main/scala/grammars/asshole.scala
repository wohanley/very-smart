package grammars


case class Asshole(topic: Action, advice: Action, criticism: Action) {

  // typing conveniences
  def nt(name: String) = Nonterminal(name)
  def tm(value: String) = Terminal(value)
  def oneNt(name: String) = Seq(nt(name))
  def oneTm(value: String) = Seq(tm(value))
  def tms(values: String*): Set[Seq[Symbol]] = values.map(oneTm).toSet

  val grammar: Map[LeftSide, RightSide] = Map(
    nt("start") -> Set(Seq(nt("greeting"), tm(", "), nt("body"))),
    nt("greeting") -> Set(
      oneNt("attention"),
      Seq(nt("attention"), tm(" "), nt("title"))),
    nt("attention") -> tms("hey", "sup", "yo", "hi", "what up"),
    nt("title") -> tms("bro", "dude", "man", "girl", "my man", "little lady"),
    nt("body") -> Set(
      Seq(
        nt("investigation"), tm(" "),
        nt("criticism"), tm(" "),
        nt("advice")
      ),
      oneNt("investigation"),
        Seq(
          nt("investigation"), tm(" "),
          nt("criticism")
        ),
      Seq(
        nt("investigation"), tm(" "),
        nt("advice")
      )),
    nt("investigation") -> Set(
      Seq(tm("looks like "), topic)
    )
  )

  def randomText(): Option[String] = grammars.randomText(grammar)
}
