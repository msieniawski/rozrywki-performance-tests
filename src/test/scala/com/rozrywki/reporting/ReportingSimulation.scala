package com.rozrywki.reporting

import com.rozrywki.Constants
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class ReportingSimulation extends Simulation {
    
    private val httpProtocol = http
        .baseUrl(Constants.REPORTING_URL)
        .acceptHeader("application/json")
    
    private val scn = scenario("place-bet-slip")
        .exec(
            http("place-bet-slip-request")
                .post("/bet-slip-placed")
                .body(RawFileBody("bodies/place-bet-slip.json")).asJson
        ).pause(5)
    
    setUp(
        scn.inject(atOnceUsers(10))
    ).protocols(httpProtocol)
}