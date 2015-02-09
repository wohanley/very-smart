package grammars


object Main {
  def main(args: Array[String]) = {
    println(Asshole(Action(() => Seq(Terminal("ham sandwich"))), EmptyAction, EmptyAction).randomText())
  }
}
