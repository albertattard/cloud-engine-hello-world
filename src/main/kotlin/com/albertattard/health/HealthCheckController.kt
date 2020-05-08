package com.albertattard.health

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("health")
class HealthCheckController {

  @Get("/", produces = [MediaType.APPLICATION_JSON])
  fun here(): Map<String, String> =
    mapOf("Hello?" to "I'm alive!!")
}
