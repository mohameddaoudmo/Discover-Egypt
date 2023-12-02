package com.example.discoveregypt.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.discoveregypt.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)
val firaSansFamily = FontFamily(
    Font(R.font.quilight, FontWeight.Light),
    Font(R.font.quiregular, FontWeight.Normal),
    Font(R.font.quimeduim, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.quimeduim, FontWeight.Medium),
    Font(R.font.quibold, FontWeight.Bold)
)
val notosan = FontFamily(
    Font(R.font.notosanspahawhhmongregular, FontWeight.Light),

)