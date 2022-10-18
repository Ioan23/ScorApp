package com.xaivision.scorapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.xaivision.scorapp.R

val Roboto = FontFamily(
    Font(R.font.roboto_regular, FontWeight.Light),
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_bold, FontWeight.Bold)
)

val typography = Typography(
    defaultFontFamily = Roboto,
    h1 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 28.13.sp,
        letterSpacing = 0.2.sp
    ),
    h2 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 24.26.sp,
        letterSpacing = 0.2.sp
    ),
    h3 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 16.8.sp,
        letterSpacing = 0.2.sp,
    ),
    h4 = TextStyle(
        fontWeight = FontWeight.Light,
        fontSize = 16.sp,
        lineHeight = 16.8.sp,
        letterSpacing = 0.2.sp
    ),
    h5 = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 16.8.sp,
        letterSpacing = 0.2.sp
    ),
    h6 = TextStyle(
        fontWeight = FontWeight.Light,
        fontSize = 36.sp,
        lineHeight = 16.8.sp,
        letterSpacing = 0.2.sp
    ),
    subtitle1 = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 22.4.sp,
        letterSpacing = 0.2.sp
    ),
    subtitle2 = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 19.6.sp,
        letterSpacing = (-0.41).sp
    ),
    body1 = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 22.4.sp,
        letterSpacing = (-0.41).sp
    ),
    body2 = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Light,
        lineHeight = 21.sp,
    ),
    button = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 19.6.sp,
    ),
    caption = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 16.8.sp,
        letterSpacing = (-0.41).sp
    )
)
