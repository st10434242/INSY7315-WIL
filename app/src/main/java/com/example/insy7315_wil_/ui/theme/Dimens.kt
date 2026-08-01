package com.example.insy7315_wil_.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object SgulaSpacing {
    val x1: Dp = 4.dp
    val x2: Dp = 8.dp
    val x3: Dp = 12.dp
    val x4: Dp = 16.dp
    val x5: Dp = 24.dp
    val x6: Dp = 32.dp
    val x7: Dp = 40.dp
    val x8: Dp = 48.dp
    val x9: Dp = 64.dp
}

object SgulaRadius {
    val Sm: Dp = 8.dp
    val Md: Dp = 12.dp
    val Lg: Dp = 16.dp

    val SmShape: Shape = RoundedCornerShape(Sm)
    val MdShape: Shape = RoundedCornerShape(Md)
    val LgShape: Shape = RoundedCornerShape(Lg)
    val FullShape: Shape = CircleShape
}

object SgulaElevation {
    val Sm: Dp = 2.dp
    val Md: Dp = 6.dp
    val Lg: Dp = 12.dp
}

object SgulaLayout {
    val ContentMax: Dp = 1100.dp

    // under this width we show the bottom tab bar, over it the top nav
    val CompactBreakpoint: Dp = 1024.dp

    val TabBarHeight: Dp = 72.dp
    val TopNavHeight: Dp = 72.dp

    // accessibility minimum, everything tappable must be at least this
    val MinTouchTarget: Dp = 48.dp
}

// use this instead of Modifier.shadow() so the shadow keeps the warm tint
fun Modifier.sgulaShadow(elevation: Dp, shape: Shape): Modifier =
    this.shadow(
        elevation = elevation,
        shape = shape,
        clip = false,
        ambientColor = SgulaColors.Shadow,
        spotColor = SgulaColors.Shadow,
    )
