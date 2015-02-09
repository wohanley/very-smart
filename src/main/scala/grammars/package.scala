package object grammars {

  /** Products are things that can be on the right-hand side of a grammar
    * rule. */
  trait Product
  case class Symbol(name: String) extends Product
  case class Action(f: () => Product) extends Product
  case class Terminal(value: String) extends Product

  type LeftSide = Symbol
  type RightSide = Seq[Product]

  type Grammar = Map[LeftSide, RightSide]

  def start(grammar: Grammar): Option[RightSide] =
    grammar.find({
      case (key, _) => key.name == "start"
    }).map({
      case (_, product) => product
    })
}
