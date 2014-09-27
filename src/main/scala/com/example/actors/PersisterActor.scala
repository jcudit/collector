package com.example.actors

import akka.actor.Actor
import akka.actor.ActorLogging
import com.example.actors.CollectorMessages._

class PersisterActor extends Actor with ActorLogging {

  def receive = {
    case Persist(located) => {

      /* Your code here to persist IP address and lat/lon pairs to any database */
      // Optionally use a circuit breaker to buffer messages in-memory until the db issue is resolved (http://doc.akka.io/docs/akka/snapshot/common/circuitbreaker.html)

      log.info("Persisting")
    }
  }
}
