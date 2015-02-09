package grammars

import scala.collection.immutable.HashMap


object Asshole {

  val grammar: Map[LeftSide, RightSide] = Map(
    Nonterminal("start") -> Set(Seq(Terminal("Done.")))
  )

  def randomText(): Option[String] = grammars.randomText(grammar)
}
