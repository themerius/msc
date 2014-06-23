package scai

import akka.actor.Actor
import akka.actor.Props

case class Msg(m: String)

class Simple extends Actor {
  def receive = {
    case Msg(m) =>
      println(m)
      context.system.shutdown
  }
}

object Main {
  def main(args: Array[String]) {
    import akka.actor.ActorSystem
    val system = ActorSystem("mysystem")
    val simple = system.actorOf(Props[Simple], "first")
    simple ! Msg("Hello World")
  }
}