package com.albertattard.health

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MicronautTest

@MicronautTest
class HealthCheckControllerTest(
  @Client("/health") private val client: RxHttpClient
) : StringSpec({

  "should return 200 when accessed" {
    val result = client.toBlocking().exchange("/", String::class.java)
    result.code() shouldBe 200
  }
})
