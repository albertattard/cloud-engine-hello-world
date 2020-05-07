package com.albertattard.quotes

import io.micronaut.core.annotation.Introspected

@Introspected
data class CreateQuote(val quote: String, val author: String)
