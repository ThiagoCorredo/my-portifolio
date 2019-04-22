package com.tcorredo.myportifolio.util.ui

import android.annotation.SuppressLint
import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment
import com.google.android.material.shape.ShapePath

/**
 * @author Thiago Corredo
 * @since 2019-03-13
 */
class BottomAppBarCutCornersTopEdge(
  private val fabMargin: Float,
  roundedCornerRadius: Float,
  private val cradleVerticalOffset: Float
) : BottomAppBarTopEdgeTreatment(fabMargin, roundedCornerRadius, cradleVerticalOffset) {

  @SuppressLint("RestrictedApi")
  override fun getEdgePath(
    length: Float,
    center: Float,
    interpolation: Float,
    shapePath: ShapePath
  ) {
    val fabDiameter = fabDiameter
    if (fabDiameter == 0f) {
      shapePath.lineTo(length, 0f)
      return
    }

    val diamondSize = fabDiameter / 2f
    val middle = center + horizontalOffset

    val verticalOffsetRatio = cradleVerticalOffset / diamondSize
    if (verticalOffsetRatio >= 1.0f) {
      shapePath.lineTo(length, 0f)
      return
    }

    shapePath.lineTo(middle - (fabMargin + diamondSize - cradleVerticalOffset), 0f)

    shapePath.lineTo(middle, (diamondSize - cradleVerticalOffset + fabMargin) * interpolation)

    shapePath.lineTo(middle + (fabMargin + diamondSize - cradleVerticalOffset), 0f)

    shapePath.lineTo(length, 0f)
  }
}