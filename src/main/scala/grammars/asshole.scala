package grammars

import scala.collection.immutable.HashMap


object Asshole {

  // typing conveniences
  def nt(name: String) = Nonterminal(name)
  def tm(value: String) = Terminal(value)

  val grammar: Map[LeftSide, RightSide] = Map(
    nt("start") -> Set(Seq(tm("Done.")))
  )

  def randomText(): Option[String] = grammars.randomText(grammar)
}
