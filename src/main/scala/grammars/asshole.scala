package grammars


case class Asshole(topic: Action, advice: Action) {

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
        nt("adviceWrapper")
      ),
      Seq(
        nt("investigation"), tm(" "),
        nt("backhand")
      ),
      Seq(
        nt("investigation"), tm(" "),
        nt("adviceWrapper")
      )),
    nt("investigation") -> Set(
      Seq(tm("looks like "), topic, tm(".")),
      Seq(tm("is that "), topic, tm("?")),
      Seq(tm("what you got there, "), topic, tm("?"))
    ),
    nt("criticism") -> Set(
      Seq(nt("backhand"), tm(", but")),
      Seq(nt("straightCrit"))
    ),
    nt("backhand") -> Set(
      oneTm("that's alright"),
      oneTm("fuckin sweet. love it"),
      oneTm("your doin alright"),
      oneTm("dope"),
      oneTm("that's cool")
    ),
    nt("straightCrit") -> Set(
      oneTm("actually,"),
      oneTm("not quite!"),
      oneTm("good try, but"),
      oneTm("ah, see,"),
      oneTm("I did this for years.")
    ),
    nt("adviceWrapper") -> Set(
      Seq(advice),
      Seq(tm("don't forget to "), advice),
      Seq(tm("make sure you "), advice),
      Seq(tm("gotta "), advice),
      Seq(tm("did you "), advice, tm("?")),
      Seq(tm("yeah, I always "), advice)
    )
  )

  def randomText(): Option[String] = grammars.randomText(grammar)
}
