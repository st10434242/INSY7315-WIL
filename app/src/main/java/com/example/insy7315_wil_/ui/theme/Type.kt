package com.example.insy7315_wil_.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

val SgulaFontFamily: FontFamily = FontFamily.Default

// converted from the css rem sizes, 1rem = 16sp
object SgulaFontSize {
    val Xs: TextUnit = 13.sp
    val Sm: TextUnit = 14.sp
    val Base: TextUnit = 16.sp
    val Lg: TextUnit = 18.sp
    val Xl: TextUnit = 20.sp
    val Xxl: TextUnit = 24.sp
    val Xxxl: TextUnit = 30.sp
    val Display: TextUnit = 36.sp
}

object SgulaTextStyles {
    val H1 = TextStyle(
        fontFamily = SgulaFontFamily,
        fontSize = SgulaFontSize.Xxl,
        fontWeight = FontWeight.Bold,
        lineHeight = 30.sp,
        color = SgulaColors.Text,
    )

    val H2 = TextStyle(
        fontFamily = SgulaFontFamily,
        fontSize = SgulaFontSize.Xl,
        fontWeight = FontWeight.Bold,
        lineHeight = 25.sp,
        color = SgulaColors.Text,
    )

    val H3 = TextStyle(
        fontFamily = SgulaFontFamily,
        fontSize = SgulaFontSize.Lg,
        fontWeight = FontWeight.Bold,
        lineHeight = 23.sp,
        color = SgulaColors.Text,
    )

    val Body = TextStyle(
        fontFamily = SgulaFontFamily,
        fontSize = SgulaFontSize.Base,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
        color = SgulaColors.Text,
    )

    val BodySecondary = Body.copy(color = SgulaColors.TextSecondary)

    val BodySmall = TextStyle(
        fontFamily = SgulaFontFamily,
        fontSize = SgulaFontSize.Sm,
        fontWeight = FontWeight.Normal,
        lineHeight = 21.sp,
        color = SgulaColors.Text,
    )

    val Caption = TextStyle(
        fontFamily = SgulaFontFamily,
        fontSize = SgulaFontSize.Xs,
        fontWeight = FontWeight.Normal,
        lineHeight = 19.sp,
        color = SgulaColors.TextTertiary,
    )

    val Label = TextStyle(
        fontFamily = SgulaFontFamily,
        fontSize = SgulaFontSize.Sm,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 21.sp,
        color = SgulaColors.Text,
    )

    val Button = TextStyle(
        fontFamily = SgulaFontFamily,
        fontSize = SgulaFontSize.Base,
        fontWeight = FontWeight.SemiBold,
        lineHeight = SgulaFontSize.Base,
    )

    val ButtonSmall = Button.copy(fontSize = SgulaFontSize.Sm, lineHeight = SgulaFontSize.Sm)

    val Eyebrow = TextStyle(
        fontFamily = SgulaFontFamily,
        fontSize = SgulaFontSize.Xs,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.78.sp,
        lineHeight = 19.sp,
        color = SgulaPalette.Plum600,
    )

    val Affirmation = TextStyle(
        fontFamily = SgulaFontFamily,
        fontSize = SgulaFontSize.Xl,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 33.sp,
        color = SgulaPalette.Plum900,
    )

    val RingValue = TextStyle(
        fontFamily = SgulaFontFamily,
        fontSize = SgulaFontSize.Xxl,
        fontWeight = FontWeight.Bold,
        lineHeight = 30.sp,
        color = SgulaPalette.Plum800,
    )

    val Link = Body.copy(
        color = SgulaPalette.Plum700,
        textDecoration = TextDecoration.Underline,
    )
}
