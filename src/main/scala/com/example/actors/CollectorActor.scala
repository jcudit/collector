package com.example.actors

import akka.actor.Actor
import akka.actor.Props
import akka.actor.ActorLogging
import scala.concurrent.duration._

import com.example.actors.CollectorMessages._

class CollectorActor extends Actor with ActorLogging {

  val scraper = context.actorOf(Props[ScraperActor], "scraper")
  val parser = context.actorOf(Props[ParserActor], "parser")
  val locator = context.actorOf(Props[LocatorActor], "locator")
  val persister = context.actorOf(Props[PersisterActor], "persister")

  // Schedule scrapes
  log.info("Scheduling initial scrape")
  scraper ! Scrape
  
  def scheduleScrape() = {
    import scala.concurrent.ExecutionContext.Implicits.global
    context.system.scheduler.scheduleOnce(30 minutes, scraper, Scrape)
    log.info("Scheduling another scrape in 30 minutes")
  }

  def receive = {
    // Site has been re-scraped, schedule the next scrape
    case AckScrape(source) => {
      scheduleScrape
      // Send page source to parser
      log.info("Scrape complete. Starting parse.")
      parser ! ParsePage(source)
    }
    // Page has been parsed and a list of IP addresses has been produced
    case ParseResult(ips) => {
      // Dispatch list of IPs to the geolookup service
      log.info("Parse complete. Starting geolookup.")
      locator ! LocateIPs(ips)
    }
    // Receiving a list of IPs that have been mapped to a lat/lon pair
    case LocateIPsResult(located) => {
      // Write IP to (lat,lon) pair to some database
      log.info("Geolookup complete. Starting persistence.")
      persister ! Persist(located)
    }
  }
}

object CollectorMessages {
  case object Scrape
  case class AckScrape(source:String)
  case class ParsePage(source:String)
  case class ParseResult(ips:List[String])
  case class LocateIPs(ips:List[String])
  case class LocateIPsResult(located:List[Map[String,(Double,Double)]])
  case class Persist(located:List[Map[String,(Double,Double)]])
}
