// from: http://doc.akka.io/docs/akka/snapshot/scala/testkit-example.html

import scala.util.Random
import scala.language.postfixOps
import scala.concurrent.duration._
 
import org.scalatest.BeforeAndAfterAll
import org.scalatest.WordSpecLike
import org.scalatest.Matchers
 
import com.typesafe.config.ConfigFactory
 
import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import akka.testkit.DefaultTimeout
import akka.testkit.ImplicitSender
import akka.testkit.TestKit


class TestKitUsageSpec
  extends TestKit(
    ActorSystem("TestKitUsageSpec",
    ConfigFactory.parseString(TestKitUsageSpec.config)))
  with DefaultTimeout with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  import TestKitUsageSpec._

  val echoRef = system.actorOf(Props[EchoActor])

  override def afterAll {
    system.shutdown()
  }

  "An EchoActor" should {
    "Respond with the same message it receives" in {
      within(500 millis) {
        echoRef ! "test"
        expectMsg("test")
      }
    }
  }

}


object TestKitUsageSpec {

  val config = """
    akka {
      loglevel = "WARNING"
    }
  """

  class EchoActor extends Actor {
    def receive = {
      case msg => sender ! msg
    }
  }

}