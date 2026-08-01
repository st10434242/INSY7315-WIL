package com.example.insy7315_wil_.ui.theme

import androidx.compose.ui.graphics.Color

// these used to be called sage back when the palette was green, it's plum now
object SgulaPalette {
    val Plum50 = Color(0xFFF4EEF0)
    val Plum100 = Color(0xFFE7D9DD)
    val Plum200 = Color(0xFFD0B7BF)
    val Plum300 = Color(0xFFB3919D)
    val Plum400 = Color(0xFF93707E)
    val Plum500 = Color(0xFF765565)
    val Plum600 = Color(0xFF5F4351)
    val Plum700 = Color(0xFF4C3641)
    val Plum800 = Color(0xFF3C2B33)
    val Plum900 = Color(0xFF2E2128)

    val Cream0 = Color(0xFFFBF8F2)
    val Cream50 = Color(0xFFF6F1E8)
    val Surface = Color(0xFFFFFFFF)
    val WarmGray100 = Color(0xFFECE6DA)
    val WarmGray200 = Color(0xFFDDD4C2)
    val WarmGray300 = Color(0xFFC3B8A4)
    val WarmGray400 = Color(0xFFA3947D)
    val Ink900 = Color(0xFF2E2A22)
    val Ink700 = Color(0xFF55503F)
    val Ink500 = Color(0xFF7D7561)
    val Ink300 = Color(0xFFA89F8A)

    val Accent100 = Color(0xFFFBE6CF)
    val Accent300 = Color(0xFFF0BD82)
    val Accent500 = Color(0xFFE0954A)
    val Accent600 = Color(0xFFC17835)
    val Accent700 = Color(0xFF9C5E29)

    val Danger100 = Color(0xFFF4DCD8)
    val Danger300 = Color(0xFFDBA39B)
    val Danger500 = Color(0xFFB6584A)
    val Danger600 = Color(0xFF954739)
    val Danger700 = Color(0xFF7A3A2F)
}

object SgulaColors {
    val Background = SgulaPalette.Cream0
    val Surface = SgulaPalette.Surface
    val SurfaceSunken = SgulaPalette.Cream50
    val Border = SgulaPalette.WarmGray200

    val Primary = SgulaPalette.Plum500
    val PrimaryHover = SgulaPalette.Plum600
    val PrimaryActive = SgulaPalette.Plum700
    val PrimarySoft = SgulaPalette.Plum100

    val Accent = SgulaPalette.Accent500
    val AccentHover = SgulaPalette.Accent600
    val AccentSoft = SgulaPalette.Accent100

    val Danger = SgulaPalette.Danger500
    val DangerHover = SgulaPalette.Danger600
    val DangerSoft = SgulaPalette.Danger100

    val Text = SgulaPalette.Ink900
    val TextSecondary = SgulaPalette.Ink700
    val TextTertiary = SgulaPalette.Ink500
    val TextDisabled = SgulaPalette.Ink300
    val TextOnPrimary = Color(0xFFFFFFFF)

    val FocusRing = Color(0xFF3D6FA8)

    val Scrim = Color(0x732E2A22)

    val Shadow = Color(0xFF3C321E)
}

// rough -> great, order matters
val MoodToneColors: List<Color> = listOf(
    SgulaPalette.WarmGray300,
    SgulaPalette.Plum300,
    SgulaPalette.Plum400,
    SgulaPalette.Plum500,
    SgulaPalette.Accent500,
)
