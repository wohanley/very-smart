package object grammars {

  trait Symbol
  type Production = Seq[Symbol]
  case class Nonterminal(name: String) extends Symbol
  case class Terminal(value: String) extends Symbol
  case class Action(f: () => Production) extends Symbol

  object EmptyAction extends Action(() => Seq(Terminal("")))

  def TerminalAction(value: String): Action =
    Action(() => Seq(Terminal(value)))

  type LeftSide = Nonterminal
  type RightSide = Set[Production]

  type Grammar = Map[LeftSide, RightSide]

  def takeRandom[T](xs: Traversable[T]): Option[T] =
    /** toVector is necessary here in case a Set gets passed in. For some
      * reason shuffle is defined on Traversable (as opposed to Iterable)?
      * I'm not sure that makes any sense. *shrug* */
    util.Random.shuffle(xs.toVector).headOption

  def produceRandom(grammar: Grammar, left: LeftSide): Option[Production] =
    grammar.get(left).flatMap(productions => takeRandom(productions))

  def reduceProduction(grammar: Grammar, production: Production): Seq[String] =
    (for (symbol <- production) yield reduceSymbol(grammar, symbol)).flatten

  def reduceSymbol(grammar: Grammar, symbol: Symbol): Seq[String] =
    reduceSymbol(Seq.empty[String], grammar, symbol)

  def reduceSymbol(result: Seq[String], grammar: Grammar, symbol: Symbol):
      Seq[String] =
    symbol match {
      case Terminal(value) => result :+ value
      case Nonterminal(name) =>
        produceRandom(grammar, Nonterminal(name)) match {
          case None => Seq.empty[String]
          case Some(production) => reduceProduction(grammar, production)
        }
      case Action(f) => reduceProduction(grammar, f())
    }

  def start(grammar: Grammar): Option[Production] =
    produceRandom(grammar, Nonterminal("start"))

  def randomText(grammar: Grammar): Option[String] = {
    produceRandom(grammar, Nonterminal("start")).map(production =>
      reduceProduction(grammar, production).mkString
    )
  }
}
