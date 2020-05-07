package com.albertattard.quotes

import io.micronaut.context.event.StartupEvent
import io.micronaut.runtime.event.annotation.EventListener
import io.micronaut.scheduling.annotation.Async
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory
import java.nio.charset.StandardCharsets
import javax.inject.Singleton

@Singleton
open class SampleDataLoader(private val database: Database) {

  companion object {
    @JvmStatic
    private val LOGGER = LoggerFactory.getLogger(SampleDataLoader::class.java)
  }

  @Async
  @EventListener
  open fun loadConferenceData(event: StartupEvent) {
    LOGGER.debug("Preparing sample quotes")
    transaction(database) {
      if (QuotesTable.selectAll().count() == 0) {
        LOGGER.debug("Database is empty, populating the database with sample quotes")

        javaClass.getResourceAsStream("/quotes.txt")
          .reader(StandardCharsets.UTF_8)
          .useLines { lines ->
            lines
              .map { it.split("\\|".toRegex()) }
              .filter { it.size == 2 }
              .forEach { parts ->
                LOGGER.debug("Adding quote {} by {}", parts[0], parts[1])
                QuotesTable.insert {
                  it[quote] = parts[0]
                  it[author] = parts[1]
                }
              }
          }
      } else {
        LOGGER.debug("Database is primed already")
      }
    }
  }
}
