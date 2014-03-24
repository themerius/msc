import scala.tools.nsc._
import scala.tools.nsc.interpreter._

val n = new IMain(new Settings())
n.bind("label", "Int", new Integer(4));
n.interpret("println(2+label)");
n.close();

class SomeClass

val settings = new Settings
settings.embeddedDefaults[SomeClass]  // only needed in sbt
val n = new IMain(settings)
n.interpret("""
val y = 5
val x = 10
""");
println(n.valueOfTerm("y").get)
n.close();