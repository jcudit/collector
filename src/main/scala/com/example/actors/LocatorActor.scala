package com.example.actors

import akka.actor.Actor
import akka.actor.ActorLogging
import com.example.actors.CollectorMessages._

class LocatorActor extends Actor with ActorLogging {

  def receive = {
    case LocateIPs(ips) => {

      /* Your code here to lookup IP address to lat/lon pairs via http://freegeoip.net/ */
      // Optionally rate limit the calls to 10 per second (http://doc.akka.io/docs/akka/snapshot/contrib/throttle.html)

      val dummyMapping:List[Map[String,(Double,Double)]] = List(Map("1.1.1.1" -> (1.1,2.2)))
      log.info("Locating")
      sender ! LocateIPsResult(dummyMapping)
    }
  }
}
