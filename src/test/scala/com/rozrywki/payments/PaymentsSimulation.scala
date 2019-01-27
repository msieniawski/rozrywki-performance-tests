package com.rozrywki.payments

import java.util.UUID

import com.rozrywki.Constants
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class PaymentsSimulation extends Simulation {
    
    private var id = ""
    
    private val httpProtocol = http
        .baseUrl(Constants.PAYMENTS_URL)
        .acceptHeader("application/json")
    
    private val scn = scenario("add-player")
        .exec(
            http("add-player-request")
                .post("/api/players")
                .body(StringBody(_ =>
                    s"""{ "playerId": "${
                        id = UUID.randomUUID().toString
                        id
                    }" }""")).asJson
        ).pause(5)
        .exec(
            http("get-player-request")
                .get(s"/api/players/$id")
        ).pause(7)
    
    setUp(
        scn.inject(atOnceUsers(10))
    ).protocols(httpProtocol)
}
