package com.tcorredo.myportifolio.util

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import androidx.annotation.RequiresPermission

/**
 * @author Thiago Corredo
 * @since 2019-04-18
 */
object NetworkUtils {
  @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
  fun isNetworkConnected(context: Context): Boolean {
    val cm: ConnectivityManager =
      context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return cm.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected
  }
}