package com.tcorredo.myportifolio.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @author Thiago Corredo
 * @since 2019-03-15
 */
@Parcelize
data class State(
  var id: String,
  var name: String
) : Parcelable