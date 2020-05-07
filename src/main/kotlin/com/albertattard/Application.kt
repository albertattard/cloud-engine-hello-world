package com.albertattard

import io.micronaut.runtime.Micronaut

object Application {

  @JvmStatic
  fun main(args: Array<String>) {
    Micronaut.build()
      .packages("com.albertattard")
      .mainClass(Application.javaClass)
      .start()
  }
}
