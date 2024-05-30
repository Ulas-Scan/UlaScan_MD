package com.ulascan.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ulascan.app.R

val mulishFontFamily = FontFamily(
    Font(R.font.mulish_extralight, FontWeight.ExtraLight),
    Font(R.font.mulish_light, FontWeight.Light),
    Font(R.font.mulish, FontWeight.Normal),
    Font(R.font.mulish_medium, FontWeight.Medium),
    Font(R.font.mulish_semibold, FontWeight.SemiBold),
    Font(R.font.mulish_bold, FontWeight.Bold),
    Font(R.font.mulish_extrabold, FontWeight.ExtraBold),
    Font(R.font.mulish_black, FontWeight.Black),
)

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = mulishFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = mulishFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    titleSmall = TextStyle(
        fontFamily = mulishFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelLarge = TextStyle(
        fontFamily = mulishFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    labelMedium = TextStyle(
        fontFamily = mulishFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = mulishFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)