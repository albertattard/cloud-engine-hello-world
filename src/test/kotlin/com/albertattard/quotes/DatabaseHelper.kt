package com.albertattard.quotes

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseHelper {

  fun <T> runAndRollback(database: Database, block: (Transaction) -> T) =
    transaction(database) {
      val result = block(this)
      rollback()
      result
    }

  private fun readQuote(id: Long): Map<String, Any> =
    QuotesTable.select { QuotesTable.id eq id }
      .singleOrNull()
      ?.let {
        mapOf(
          "id" to it[QuotesTable.id].value,
          "quote" to it[QuotesTable.quote],
          "author" to it[QuotesTable.author]
        )
      } ?: emptyMap()

  fun withQuote(id: Long, block: (Map<String, Any>) -> Unit) =
    readQuote(id).let {
      block(it)
      it["id"] as Long
    }

  fun emptyDatabase() {
    QuotesTable.deleteAll()
  }

  fun createQuote(quote: CreateQuote) =
    QuotesTable.insertAndGetId {
      it[QuotesTable.quote] = quote.quote
      it[QuotesTable.author] = quote.author
    }.let {
      CreatedQuote(id = it.value)
    }

  fun countQuotes(): Int =
    QuotesTable.selectAll().count()
}
