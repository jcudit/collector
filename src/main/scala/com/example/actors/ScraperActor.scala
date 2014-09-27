package com.example.actors

import akka.actor.Actor
import akka.actor.ActorLogging
import com.example.actors.CollectorMessages._

class ScraperActor extends Actor with ActorLogging {

  def receive = {
    case Scrape => {

      /* Your code here to scrape http://torstatus.blutmagie.de */

      val dummyScrape = "<html><body><p>1.1.1.1</p></body></html>"
      log.info("Scraping")
      sender ! AckScrape(dummyScrape)
    }
  }

}
