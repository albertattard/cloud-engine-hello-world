package com.albertattard.quotes

import org.jetbrains.exposed.dao.id.LongIdTable

object QuotesTable : LongIdTable("quotes") {
  val quote = varchar("quote", 255)
  val author = varchar("author", 255)
}
