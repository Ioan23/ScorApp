package com.xaivision.scorapp.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val MVIDemoShapes = Shapes(
    small = RoundedCornerShape(6.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(12.dp),
)

private val BOTTOM_SHEET_TOP_RADIUS = 16.dp
val BottomSheetShape =
    RoundedCornerShape(
        topEnd = BOTTOM_SHEET_TOP_RADIUS,
        topStart = BOTTOM_SHEET_TOP_RADIUS
    )
