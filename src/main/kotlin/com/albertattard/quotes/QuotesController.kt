package com.albertattard.quotes

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/")
class QuotesController(private val service: QuotesService) {

  @Get("/", produces = [MediaType.APPLICATION_JSON])
  fun list(): List<Quote> =
    service.list()
}
