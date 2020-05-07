package com.albertattard.quotes

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest.GET
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MicronautTest
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.kotlintest.MicronautKotlinTestExtension.getMock
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

@MicronautTest
class QuotesControllerTest(
  private val service: QuotesService,
  @Client("/") private val client: RxHttpClient
) : StringSpec({

  "should return the list of quotes provided by the service" {
    val mock = getMock(service)

    val quotes = listOf(
      Quote(
        id = 1,
        quote = "Two things are infinite: the universe and human stupidity; and I'm not sure about the universe.",
        author = "Albert Einstein"
      )
    )
    every { mock.list() } returns quotes

    val result = client.toBlocking().exchange(GET<String>("/"), Argument.of(List::class.java, Quote::class.java))
    result.code() shouldBe 200
    result.body() shouldBe quotes

    verify(exactly = 1) { mock.list() }

    // verify(exactly = 2) { mock.hashCode() }
    // verify(exactly = 1) { mock.toString() }
    // confirmVerified(mock)
  }
}) {
  @MockBean(QuotesService::class)
  fun quotesService(): QuotesService =
    mockk()
}
