package com.rozrywki.core

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class FetchMatchesSimulation extends Simulation {
    
    private val httpProtocol = http
        .baseUrl("http://34.209.198.6:8080")
        .acceptHeader("application/json")
    
    private val scn = scenario("fetch-matches")
        .exec(
            http("fetch-matches-request")
                .post("/matches")
        ).pause(5)
    
    setUp(
        scn.inject(atOnceUsers(10))
    ).protocols(httpProtocol)
}