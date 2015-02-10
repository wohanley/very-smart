package object nlp {

  import java.io.FileInputStream
  import opennlp.tools.cmdline.parser.ParserTool
  import opennlp.tools.parser.Parse
  import opennlp.tools.parser.ParserFactory
  import opennlp.tools.parser.ParserModel


  object English {

    val parser = ParserFactory.create(
      new ParserModel(
        new FileInputStream("en-parser-chunking.bin")))

    def parseLine(line: String): Array[Parse] =
      ParserTool.parseLine(line, parser, 1)

    /** I assume the first noun phrase is the topic of the text. Pretty sound
      * imo */
    def topic(text: String): Option[String] = {

      parseLine(text)
        .headOption
        .map(parse => nounPhrases(Seq.empty[Parse], parse))
        .flatMap(nps => nps.headOption)
        .map(np => np.toString())
    }

    /** Search through a parse tree for noun phrases. */
    def nounPhrases(nps: Seq[Parse], node: Parse): Seq[Parse] = {

      val npsWithNode: Seq[Parse] = node.getType() match {
        case "NP" => nps.+:(node)
        case _ => nps
      }

      // flatten does maintain order, as far as I can tell
      npsWithNode ++ node.getChildren()
        .map(p => nounPhrases(nps, p)).flatten.toSeq
    }

    /** This just strips periods for now, enough for my purposes */
    def stripPunctuation(text: String): String =
      text.replace(".", "")
  }
}
