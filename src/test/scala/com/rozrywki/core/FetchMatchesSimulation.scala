package com.rozrywki.core

import com.rozrywki.Constants
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class FetchMatchesSimulation extends Simulation {
    
    private val httpProtocol = http
        .baseUrl(Constants.CORE_URL)
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