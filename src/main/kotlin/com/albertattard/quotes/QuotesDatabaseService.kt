package com.albertattard.quotes

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import javax.inject.Singleton

@Singleton
class QuotesDatabaseService(private val database: Database) : QuotesService {
  override fun list(): List<Quote> =
    transaction(database) {
      QuotesTable
        .selectAll()
        .map {
          Quote(
            id = it[QuotesTable.id].value,
            quote = it[QuotesTable.quote],
            author = it[QuotesTable.author]
          )
        }
        .toList()
    }
}
