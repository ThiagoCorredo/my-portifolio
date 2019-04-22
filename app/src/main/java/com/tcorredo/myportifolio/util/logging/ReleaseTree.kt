package com.tcorredo.myportifolio.util.logging

import android.util.Log
import timber.log.Timber

/**
 * @author Thiago Corredo
 * @since 2018-07-18
 */
class ReleaseTree : Timber.Tree() {

  override fun isLoggable(
    tag: String?,
    priority: Int
  ): Boolean {
    // Log only WARN, ERROR, WTF
    return priority != Log.VERBOSE && priority != Log.DEBUG && priority != Log.INFO
  }

  override fun log(
    priority: Int,
    tag: String?,
    message: String,
    t: Throwable?
  ) {
    if (message.length < MAX_LOG_LENGTH) {
      if (priority == Log.ASSERT) {
        Timber.wtf(tag, message)
      } else {
        Log.println(priority, tag, message)
      }
      return
    }

    // Split by line, then ensure each line can fit into Log's maximum length.
    var i = 0
    val length = message.length
    while (i < length) {
      var newline = message.indexOf('\n', i)
      newline = if (newline != -1) newline else length
      do {
        val end = Math.min(newline, i + MAX_LOG_LENGTH)
        val part = message.substring(i, end)
        if (priority == Log.ASSERT) {
          Timber.wtf(tag, part)
        } else {
          Log.println(priority, tag, part)
        }
        i = end
      } while (i < newline)
      i++
    }
  }

  companion object {

    private val MAX_LOG_LENGTH = 4000
  }
}
