package com.albertattard.quotes

import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import javax.sql.DataSource

@Factory
class DatabaseFactory {

  @Bean
  fun database(dataSource: DataSource) =
    Database.connect(dataSource).apply {
      transaction(this) {
        SchemaUtils.createMissingTablesAndColumns(QuotesTable)
      }
    }
}
