package com.example.actors

import akka.actor.Actor
import akka.actor.ActorLogging
import com.example.actors.CollectorMessages._

class ParserActor extends Actor with ActorLogging {

  def receive = {
    case ParsePage(source) => {

      /* Your code here to parse http://torstatus.blutmagie.de */

      val dummyIpList:List[String] = List("1.1.1.1","2.2.2.2")
      log.info("Parsing")
      sender ! ParseResult(dummyIpList)
    }
  }
}
