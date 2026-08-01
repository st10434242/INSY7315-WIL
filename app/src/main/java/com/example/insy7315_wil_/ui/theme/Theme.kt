package com.example.insy7315_wil_.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.material3.LocalContentColor

// this is only here so normal Material widgets pick up our colours too.
// no dark scheme on purpose, the warm palette looks bad inverted.
private val SgulaColorScheme = lightColorScheme(
    primary = SgulaColors.Primary,
    onPrimary = SgulaColors.TextOnPrimary,
    primaryContainer = SgulaColors.PrimarySoft,
    onPrimaryContainer = SgulaPalette.Plum800,
    secondary = SgulaColors.Accent,
    onSecondary = SgulaColors.TextOnPrimary,
    secondaryContainer = SgulaColors.AccentSoft,
    onSecondaryContainer = SgulaPalette.Accent700,
    background = SgulaColors.Background,
    onBackground = SgulaColors.Text,
    surface = SgulaColors.Surface,
    onSurface = SgulaColors.Text,
    surfaceVariant = SgulaColors.SurfaceSunken,
    onSurfaceVariant = SgulaColors.TextSecondary,
    error = SgulaColors.Danger,
    onError = SgulaColors.TextOnPrimary,
    errorContainer = SgulaColors.DangerSoft,
    onErrorContainer = SgulaPalette.Danger700,
    outline = SgulaColors.Border,
    outlineVariant = SgulaPalette.WarmGray100,
    scrim = SgulaColors.Scrim,
)

private val SgulaTypography = Typography(
    headlineMedium = SgulaTextStyles.H1,
    headlineSmall = SgulaTextStyles.H2,
    titleLarge = SgulaTextStyles.H3,
    titleMedium = SgulaTextStyles.Label,
    bodyLarge = SgulaTextStyles.Body,
    bodyMedium = SgulaTextStyles.BodySmall,
    bodySmall = SgulaTextStyles.Caption,
    labelLarge = SgulaTextStyles.Button,
    labelMedium = SgulaTextStyles.Label,
    labelSmall = SgulaTextStyles.Caption,
)

// wrap every screen in this
@Composable
fun SgulaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = SgulaColorScheme,
        typography = SgulaTypography,
    ) {
        CompositionLocalProvider(
            LocalContentColor provides SgulaColors.Text,
            content = content,
        )
    }
}
