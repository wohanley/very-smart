package grammars


object Asshole {

  // typing conveniences
  def nt(name: String) = Nonterminal(name)
  def tm(value: String) = Terminal(value)
  def oneNt(name: String) = Seq(nt(name))
  def oneTm(value: String) = Seq(tm(value))
  def tms(values: String*): Set[Seq[Symbol]] = values.map(oneTm).toSet

  val grammar: Map[LeftSide, RightSide] = Map(
    nt("start") -> Set(
      oneNt("investigation"),
      Seq(nt("greeting"), tm(", "), nt("investigation"))),
    nt("greeting") -> Set(
      oneNt("attention"),
      Seq(nt("attention"), tm(" "), nt("title"))),
    nt("attention") -> tms("hey", "sup", "yo", "hi", "what up"),
    nt("title") -> tms("bro", "dude", "man", "girl", "my man", "little lady"),
    nt("investigation") -> Set(
      oneTm("that's sick"))
  )

  def randomText(): Option[String] = grammars.randomText(grammar)
}
