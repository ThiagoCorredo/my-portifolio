package com.tcorredo.myportifolio.util.logging

import timber.log.Timber

/**
 * @author Thiago Corredo
 * @since 2018-07-18
 */

class MyDebugTree : Timber.DebugTree() {
  override fun createStackElementTag(element: StackTraceElement): String? {
    return (super.createStackElementTag(element) +
        '.'.toString() +
        element.methodName +
        "[" +
        element.lineNumber +
        "]")
  }
}
