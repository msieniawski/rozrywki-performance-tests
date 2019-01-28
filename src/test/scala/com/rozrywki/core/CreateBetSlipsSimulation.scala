package com.rozrywki.core

import com.rozrywki.Constants
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class CreateBetSlipsSimulation extends Simulation {
    
    private val httpProtocol = http
        .baseUrl(Constants.CORE_URL)
        .acceptHeader("application/json")
    
    private val scn = scenario("create-bet-slips")
        .exec(
            http("create-bet-slips")
                .post("/bet-slips")
                .body(RawFileBody("bodies/create-bet-slip.json")).asJson
        ).pause(5)
        .exec(
            http("fetch-bet-slips-request")
                .get("/bet-slips")
                .queryParam("user", "test3")
                .queryParam("limit", "100")
                .queryParam("offset", "0")
        ).pause(5)
    
    setUp(
        scn.inject(atOnceUsers(10))
    ).protocols(httpProtocol)
}