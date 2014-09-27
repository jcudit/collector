collector
=========

Shell code to be filled in for an application that will periodically scrape Tor exit node IP addresses, perform a geolookup on them and then persist them to some database.

# Actor requirements in detail:

## Collector Actor

backbone that routes messages

## Scraper Actor

scrapes a URL for the page source

## Parser Actor

parses IP address out of an html page

## Locator Actor

performs a geolookup of each input IP address and produces a list of a map of each IP address to lat/lon coordinate tuple. bonus points for implementing a throttler to politely perform the geolookup

## Persister Actor

writes the IP address and geocoordinate pair to some database running in a local docker container
