package com.tcorredo.myportifolio.rest.representation

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import java.util.Date

/**
 * @author Thiago Corredo
 * @since 2019-03-11
 */
class MoshiFactory private constructor() {

  init {
    throw RuntimeException("No instances!")
  }

  companion object {

    private val MOSHI = Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
        .build()

    fun get(): Moshi {
      return MOSHI
    }
  }
}
