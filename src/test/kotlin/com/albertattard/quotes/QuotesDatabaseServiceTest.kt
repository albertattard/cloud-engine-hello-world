package com.albertattard.quotes

import com.albertattard.quotes.DatabaseHelper.createQuote
import com.albertattard.quotes.DatabaseHelper.emptyDatabase
import com.albertattard.quotes.DatabaseHelper.runAndRollback
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.micronaut.test.annotation.MicronautTest
import org.jetbrains.exposed.sql.Database

@MicronautTest
class QuotesDatabaseServiceTest(
  private val database: Database
) : StringSpec({

  "should return all quotes in the table" {
    runAndRollback(database) {
      emptyDatabase()

      val quote = "Two things are infinite: the universe and human stupidity; and I'm not sure about the universe."
      val author = "Albert Einstein"
      val created = createQuote(CreateQuote(quote = quote, author = author))

      val service = QuotesDatabaseService(database)
      val quotes = service.list()
      quotes shouldBe listOf(Quote(id = created.id, quote = quote, author = author))
    }
  }
})
