package com.albertattard.quotes

import io.micronaut.core.annotation.Introspected

@Introspected
data class Quote(val id: Long, val quote: String, val author: String)
