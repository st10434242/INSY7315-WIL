package com.example.insy7315_wil_.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.insy7315_wil_.R
import com.example.insy7315_wil_.ui.theme.MoodToneColors
import com.example.insy7315_wil_.ui.theme.SgulaColors
import com.example.insy7315_wil_.ui.theme.SgulaElevation
import com.example.insy7315_wil_.ui.theme.SgulaLayout
import com.example.insy7315_wil_.ui.theme.SgulaPalette
import com.example.insy7315_wil_.ui.theme.SgulaRadius
import com.example.insy7315_wil_.ui.theme.SgulaSpacing
import com.example.insy7315_wil_.ui.theme.SgulaTextStyles
import com.example.insy7315_wil_.ui.theme.sgulaShadow

@Composable
fun SgulaScreen(
    navItems: List<NavItem>,
    selectedRoute: String?,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier,
    brand: String = "Sgula",
    scrollable: Boolean = true,
    content: @Composable ColumnScope.() -> Unit,
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .background(SgulaColors.Background)
            .safeDrawingPadding() // keeps content off the status/nav bars
    ) {
        val expanded = maxWidth >= SgulaLayout.CompactBreakpoint
        val showNav = navItems.isNotEmpty()

        // remember must be outside the if, compose crashes if it's in a branch
        val scrollState = rememberScrollState()
        val bodyScroll = if (scrollable) Modifier.verticalScroll(scrollState) else Modifier

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = if (showNav && expanded) SgulaLayout.TopNavHeight else 0.dp,
                    bottom = if (showNav && !expanded) SgulaLayout.TabBarHeight else 0.dp,
                )
                .then(bodyScroll)
        ) {
            SgulaContainer(content = content)
            Spacer(Modifier.height(SgulaSpacing.x6))
        }

        if (showNav) {
            if (expanded) {
                SgulaTopNav(
                    brand = brand,
                    items = navItems,
                    selectedRoute = selectedRoute,
                    onNavigate = onNavigate,
                    modifier = Modifier.align(Alignment.TopCenter),
                )
            } else {
                SgulaTabBar(
                    items = navItems,
                    selectedRoute = selectedRoute,
                    onNavigate = onNavigate,
                    modifier = Modifier.align(Alignment.BottomCenter),
                )
            }
        }
    }
}

@Composable
fun SgulaContainer(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
        Column(
            modifier = Modifier
                .widthIn(max = SgulaLayout.ContentMax)
                .fillMaxWidth()
                .padding(horizontal = SgulaSpacing.x4, vertical = SgulaSpacing.x5),
            content = content,
        )
    }
}

@Composable
fun PageHeader(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
) {
    Column(
        modifier = modifier.padding(bottom = SgulaSpacing.x5),
        verticalArrangement = Arrangement.spacedBy(SgulaSpacing.x1),
    ) {
        Text(title, style = SgulaTextStyles.H1)
        if (subtitle != null) Text(subtitle, style = SgulaTextStyles.BodySecondary)
    }
}

@Composable
fun Eyebrow(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = SgulaPalette.Plum600,
) {
    Text(text.uppercase(), style = SgulaTextStyles.Eyebrow.copy(color = color), modifier = modifier)
}

enum class ButtonVariant {
    Primary,
    Secondary,
    Ghost,
    Destructive,
}

enum class ButtonSize { Default, Small }

@Composable
fun SgulaButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: ButtonVariant = ButtonVariant.Primary,
    size: ButtonSize = ButtonSize.Default,
    fullWidth: Boolean = false,
    enabled: Boolean = true,
    leadingIcon: (@Composable () -> Unit)? = null,
) {
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()

    val background = when (variant) {
        ButtonVariant.Primary -> if (pressed) SgulaColors.PrimaryActive else SgulaColors.Primary
        ButtonVariant.Secondary -> if (pressed) SgulaPalette.Plum200 else SgulaColors.PrimarySoft
        ButtonVariant.Ghost -> if (pressed) SgulaPalette.Plum50 else Color.Transparent
        ButtonVariant.Destructive -> if (pressed) SgulaColors.DangerHover else SgulaColors.Danger
    }
    val contentColor = when (variant) {
        ButtonVariant.Primary, ButtonVariant.Destructive -> SgulaColors.TextOnPrimary
        ButtonVariant.Secondary -> SgulaPalette.Plum800
        ButtonVariant.Ghost -> SgulaPalette.Plum700
    }
    val border = if (variant == ButtonVariant.Secondary) {
        BorderStroke(1.dp, SgulaPalette.Plum200)
    } else null

    val minHeight = if (size == ButtonSize.Small) 40.dp else SgulaLayout.MinTouchTarget
    val hPad = if (size == ButtonSize.Small) SgulaSpacing.x4 else 22.dp
    val vPad = if (size == ButtonSize.Small) 10.dp else 14.dp
    val textStyle = if (size == ButtonSize.Small) SgulaTextStyles.ButtonSmall else SgulaTextStyles.Button

    Row(
        modifier = modifier
            .then(if (fullWidth) Modifier.fillMaxWidth() else Modifier.wrapContentWidth())
            .heightIn(min = minHeight)
            .offset(y = if (pressed && enabled) 1.dp else 0.dp)
            .alpha(if (enabled) 1f else 0.5f)
            .clip(SgulaRadius.MdShape)
            .background(background)
            .then(if (border != null) Modifier.border(border, SgulaRadius.MdShape) else Modifier)
            .clickable(
                interactionSource = interaction,
                indication = null,
                enabled = enabled,
                role = Role.Button,
                onClick = onClick,
            )
            .padding(horizontal = hPad, vertical = vPad),
        horizontalArrangement = Arrangement.spacedBy(SgulaSpacing.x2, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (leadingIcon != null) leadingIcon()
        Text(text = text, style = textStyle.copy(color = contentColor), maxLines = 1)
    }
}

@Composable
fun SgulaCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .sgulaShadow(SgulaElevation.Sm, SgulaRadius.LgShape)
            .clip(SgulaRadius.LgShape)
            .background(SgulaColors.Surface)
            .padding(SgulaSpacing.x5),
        content = content,
    )
}

@Composable
fun CardTitle(text: String, modifier: Modifier = Modifier) {
    Text(text, style = SgulaTextStyles.H3, modifier = modifier.padding(bottom = SgulaSpacing.x2))
}

@Composable
fun CardBody(text: String, modifier: Modifier = Modifier) {
    Text(text, style = SgulaTextStyles.BodySecondary, modifier = modifier)
}

@Composable
fun AffirmationCard(
    affirmation: String,
    modifier: Modifier = Modifier,
    eyebrow: String = "Today's affirmation",
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .sgulaShadow(SgulaElevation.Md, SgulaRadius.LgShape)
            .clip(SgulaRadius.LgShape)
            .drawBehind {
                drawRect(
                    Brush.linearGradient(
                        colors = listOf(SgulaPalette.Plum100, SgulaPalette.Accent100),
                        start = Offset(0f, 0f),
                        end = Offset(size.width * 0.36f, size.height),
                    )
                )
            }
            .padding(horizontal = SgulaSpacing.x5, vertical = SgulaSpacing.x6),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Eyebrow(eyebrow, color = SgulaPalette.Accent700)
        Spacer(Modifier.height(SgulaSpacing.x2))
        Text(
            text = affirmation,
            style = SgulaTextStyles.Affirmation,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun SgulaTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    hint: String? = null,
    error: String? = null,
    optional: Boolean = false,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    isPassword: Boolean = false,
    minHeight: Dp = SgulaLayout.MinTouchTarget,
    trailing: (@Composable () -> Unit)? = null,
) {
    val interaction = remember { MutableInteractionSource() }
    val focused by interaction.collectIsFocusedAsState()
    val borderColor = when {
        error != null -> SgulaColors.Danger
        focused -> SgulaPalette.Plum400
        else -> SgulaColors.Border
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = SgulaSpacing.x4),
        verticalArrangement = Arrangement.spacedBy(SgulaSpacing.x2),
    ) {
        FieldLabel(label, optional)

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            singleLine = singleLine,
            textStyle = SgulaTextStyles.Body,
            cursorBrush = SolidColor(SgulaColors.Primary),
            interactionSource = interaction,
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = minHeight)
                .clip(SgulaRadius.MdShape)
                .background(SgulaColors.Surface)
                .border(1.dp, borderColor, SgulaRadius.MdShape),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.padding(horizontal = SgulaSpacing.x4, vertical = 13.dp),
                    verticalAlignment = if (singleLine) Alignment.CenterVertically else Alignment.Top,
                ) {
                    Box(Modifier.weight(1f)) {
                        if (value.isEmpty() && placeholder.isNotEmpty()) {
                            Text(placeholder, style = SgulaTextStyles.Body.copy(color = SgulaColors.TextTertiary))
                        }
                        innerTextField()
                    }
                    if (trailing != null) {
                        Spacer(Modifier.width(SgulaSpacing.x2))
                        trailing()
                    }
                }
            },
        )

        if (error != null) {
            Text(
                error,
                style = SgulaTextStyles.BodySmall.copy(
                    color = SgulaColors.DangerHover,
                    fontWeight = FontWeight.SemiBold,
                ),
            )
        } else if (hint != null) {
            Text(hint, style = SgulaTextStyles.BodySmall.copy(color = SgulaColors.TextTertiary))
        }
    }
}

@Composable
fun SgulaTextArea(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    hint: String? = null,
    error: String? = null,
    optional: Boolean = false,
) = SgulaTextField(
    value = value,
    onValueChange = onValueChange,
    label = label,
    modifier = modifier,
    placeholder = placeholder,
    hint = hint,
    error = error,
    optional = optional,
    singleLine = false,
    minHeight = 120.dp,
)

@Composable
private fun FieldLabel(label: String, optional: Boolean) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(label, style = SgulaTextStyles.Label)
        if (optional) {
            Text(
                " (optional)",
                style = SgulaTextStyles.Label.copy(
                    color = SgulaColors.TextTertiary,
                    fontWeight = FontWeight.Normal,
                ),
            )
        }
    }
}

@Composable
fun SgulaDropdownField(
    label: String,
    options: List<String>,
    selected: String,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier,
    error: String? = null,
) {
    var open by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = SgulaSpacing.x4),
        verticalArrangement = Arrangement.spacedBy(SgulaSpacing.x2),
    ) {
        FieldLabel(label, optional = false)

        Box {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = SgulaLayout.MinTouchTarget)
                    .clip(SgulaRadius.MdShape)
                    .background(SgulaColors.Surface)
                    .border(
                        1.dp,
                        if (error != null) SgulaColors.Danger else SgulaColors.Border,
                        SgulaRadius.MdShape,
                    )
                    .clickable(role = Role.DropdownList) { open = true }
                    .padding(horizontal = SgulaSpacing.x4, vertical = 13.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(selected, style = SgulaTextStyles.Body, modifier = Modifier.weight(1f))
                Chevron()
            }

            DropdownMenu(expanded = open, onDismissRequest = { open = false }) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option, style = SgulaTextStyles.Body) },
                        onClick = {
                            onSelect(option)
                            open = false
                        },
                    )
                }
            }
        }

        if (error != null) {
            Text(
                error,
                style = SgulaTextStyles.BodySmall.copy(
                    color = SgulaColors.DangerHover,
                    fontWeight = FontWeight.SemiBold,
                ),
            )
        }
    }
}

@Composable
private fun Chevron(color: Color = SgulaPalette.Ink500) {
    Canvas(Modifier.size(10.dp)) {
        val w = size.width
        val h = size.height
        drawLine(color, Offset(0f, h * 0.3f), Offset(w / 2f, h * 0.75f), strokeWidth = 2.dp.toPx(), cap = StrokeCap.Round)
        drawLine(color, Offset(w / 2f, h * 0.75f), Offset(w, h * 0.3f), strokeWidth = 2.dp.toPx(), cap = StrokeCap.Round)
    }
}

enum class PasswordStrength(val label: String, val filledBars: Int) {
    Weak("Weak strength", 1),
    Medium("Medium strength", 2),
    Strong("Strong strength", 3),
}

@Composable
fun PasswordStrengthMeter(strength: PasswordStrength, modifier: Modifier = Modifier) {
    val fillColor = when (strength) {
        PasswordStrength.Weak -> SgulaColors.Danger
        PasswordStrength.Medium -> SgulaColors.Accent
        PasswordStrength.Strong -> SgulaPalette.Plum500
    }
    val labelColor = when (strength) {
        PasswordStrength.Weak -> SgulaColors.DangerHover
        PasswordStrength.Medium -> SgulaPalette.Accent700
        PasswordStrength.Strong -> SgulaPalette.Plum700
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(SgulaSpacing.x2),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(SgulaSpacing.x1)) {
            repeat(3) { index ->
                Box(
                    Modifier
                        .width(24.dp)
                        .height(5.dp)
                        .clip(CircleShape)
                        .background(if (index < strength.filledBars) fillColor else SgulaPalette.WarmGray200)
                )
            }
        }
        Text(strength.label, style = SgulaTextStyles.BodySmall.copy(color = labelColor))
    }
}

data class NavItem(
    val route: String,
    val label: String,
    val icon: (@Composable (selected: Boolean) -> Unit)? = null,
)

@Composable
fun SgulaTabBar(
    items: List<NavItem>,
    selectedRoute: String?,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(SgulaLayout.TabBarHeight)
            .sgulaShadow(SgulaElevation.Md, RoundedCornerShape(0.dp))
            .background(SgulaColors.Surface),
    ) {
        items.forEach { item ->
            val selected = item.route == selectedRoute
            val tint = if (selected) SgulaPalette.Plum700 else SgulaColors.TextTertiary
            val iconSlot = item.icon

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable(role = Role.Tab) { onNavigate(item.route) },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if (iconSlot != null) {
                    iconSlot(selected)
                } else {
                    Box(
                        Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(tint.copy(alpha = if (selected) 1f else 0.55f))
                    )
                }
                Spacer(Modifier.height(SgulaSpacing.x1))
                Text(
                    item.label,
                    style = SgulaTextStyles.Caption.copy(color = tint, fontWeight = FontWeight.SemiBold),
                )
            }
        }
    }
}

@Composable
fun SgulaTopNav(
    brand: String,
    items: List<NavItem>,
    selectedRoute: String?,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(SgulaLayout.TopNavHeight)
            .sgulaShadow(SgulaElevation.Sm, RoundedCornerShape(0.dp))
            .background(SgulaColors.Surface),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier
                .widthIn(max = SgulaLayout.ContentMax)
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = SgulaSpacing.x5),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                brand,
                style = SgulaTextStyles.H3.copy(color = SgulaPalette.Plum800),
            )
            Row(horizontalArrangement = Arrangement.spacedBy(SgulaSpacing.x6)) {
                items.forEach { item ->
                    val selected = item.route == selectedRoute
                    Column(
                        modifier = Modifier
                            .width(IntrinsicSize.Max) // so the underline matches the label width
                            .clickable(role = Role.Tab) { onNavigate(item.route) }
                            .padding(vertical = SgulaSpacing.x2),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            item.label,
                            style = SgulaTextStyles.Body.copy(
                                color = if (selected) SgulaPalette.Plum700 else SgulaColors.TextSecondary,
                                fontWeight = FontWeight.SemiBold,
                            ),
                        )
                        Spacer(Modifier.height(SgulaSpacing.x2))
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(2.dp)
                                .background(if (selected) SgulaPalette.Plum500 else Color.Transparent)
                        )
                    }
                }
            }
        }
    }
}

val DefaultMoodLabels = listOf("Rough", "Low", "Calm", "Good", "Great")

@Composable
fun MoodScale(
    selectedIndex: Int?,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
    labels: List<String> = DefaultMoodLabels,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(SgulaSpacing.x2),
    ) {
        labels.forEachIndexed { index, label ->
            val selected = index == selectedIndex
            Column(
                modifier = Modifier
                    .weight(1f)
                    .heightIn(min = SgulaLayout.MinTouchTarget)
                    .clip(SgulaRadius.MdShape)
                    .background(if (selected) SgulaPalette.Plum100 else SgulaColors.SurfaceSunken)
                    .border(
                        2.dp,
                        if (selected) SgulaPalette.Plum500 else Color.Transparent,
                        SgulaRadius.MdShape,
                    )
                    .clickable(role = Role.RadioButton) { onSelect(index) }
                    .padding(horizontal = SgulaSpacing.x1, vertical = SgulaSpacing.x4),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(SgulaSpacing.x2),
            ) {
                Box(
                    Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(MoodToneColors[index.coerceIn(0, MoodToneColors.lastIndex)])
                )
                Text(
                    label,
                    style = SgulaTextStyles.Caption.copy(
                        color = if (selected) SgulaPalette.Plum800 else SgulaColors.TextSecondary,
                        fontWeight = FontWeight.SemiBold,
                    ),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
fun PointsToast(points: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .clip(SgulaRadius.MdShape)
            .background(SgulaPalette.Plum800)
            .padding(horizontal = SgulaSpacing.x4, vertical = SgulaSpacing.x2),
        horizontalArrangement = Arrangement.spacedBy(SgulaSpacing.x2),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(Modifier.size(10.dp).clip(CircleShape).background(SgulaColors.Accent))
        Text(
            "+$points points",
            style = SgulaTextStyles.BodySmall.copy(color = Color.White, fontWeight = FontWeight.Bold),
        )
    }
}

@Composable
fun StreakChip(days: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .clip(SgulaRadius.MdShape)
            .background(SgulaColors.AccentSoft)
            .padding(horizontal = SgulaSpacing.x4, vertical = SgulaSpacing.x2),
        horizontalArrangement = Arrangement.spacedBy(SgulaSpacing.x3),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            "$days",
            style = SgulaTextStyles.H3.copy(color = SgulaPalette.Accent700),
        )
        Text(
            "day streak",
            style = SgulaTextStyles.BodySmall.copy(
                color = SgulaPalette.Accent700,
                fontWeight = FontWeight.SemiBold,
            ),
        )
    }
}

@Composable
fun ProgressRing(
    progress: Float,
    modifier: Modifier = Modifier,
    caption: String = "Daily goal",
    valueLabel: String = "${(progress.coerceIn(0f, 1f) * 100).toInt()}%",
    diameter: Dp = 120.dp,
) {
    val clamped = progress.coerceIn(0f, 1f)

    Box(modifier = modifier.size(diameter), contentAlignment = Alignment.Center) {
        Canvas(Modifier.fillMaxSize()) {
            val strokeWidth = 10.dp.toPx()
            val inset = strokeWidth / 2f
            val arcSize = Size(size.width - strokeWidth, size.height - strokeWidth)
            val topLeft = Offset(inset, inset)

            drawArc(
                color = SgulaPalette.WarmGray200,
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = topLeft,
                size = arcSize,
                style = Stroke(width = strokeWidth),
            )
            drawArc(
                color = SgulaPalette.Plum500,
                startAngle = -90f, // -90 makes it start at the top
                sweepAngle = 360f * clamped,
                useCenter = false,
                topLeft = topLeft,
                size = arcSize,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(valueLabel, style = SgulaTextStyles.RingValue)
            Text(caption, style = SgulaTextStyles.Caption)
        }
    }
}

enum class PlantStage { Seed, Sprout, Growing, Blooming, Wilted }

@Composable
fun PlantStages(
    currentStage: PlantStage,
    modifier: Modifier = Modifier,
    wilted: Boolean = false,
) {
    val stages = PlantStage.values().toList()
    val effectiveCurrent = if (wilted) PlantStage.Wilted else currentStage

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(SgulaSpacing.x2),
    ) {
        stages.forEach { stage ->
            val isCurrent = stage == effectiveCurrent
            val isWiltedSlot = isCurrent && wilted

            val bg = when {
                isWiltedSlot -> SgulaColors.DangerSoft
                isCurrent -> SgulaPalette.Plum100
                else -> SgulaColors.SurfaceSunken
            }
            val fg = when {
                isWiltedSlot -> SgulaPalette.Danger700
                isCurrent -> SgulaPalette.Plum800
                else -> SgulaColors.TextTertiary
            }
            val dot = when {
                isWiltedSlot -> SgulaColors.Danger
                isCurrent -> SgulaPalette.Plum500
                else -> SgulaPalette.WarmGray300
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .clip(SgulaRadius.MdShape)
                    .background(bg)
                    .padding(horizontal = SgulaSpacing.x1, vertical = SgulaSpacing.x3),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(SgulaSpacing.x2),
            ) {
                Box(
                    Modifier
                        .size(if (isCurrent && !isWiltedSlot) 22.dp else 14.dp)
                        .clip(CircleShape)
                        .background(if (isCurrent && !isWiltedSlot) SgulaPalette.Plum200 else Color.Transparent),
                    contentAlignment = Alignment.Center,
                ) {
                    Box(Modifier.size(14.dp).clip(CircleShape).background(dot))
                }
                Text(
                    stage.name,
                    style = SgulaTextStyles.Caption.copy(color = fg, fontWeight = FontWeight.SemiBold),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
fun TrackItem(
    title: String,
    duration: String,
    isFavourite: Boolean,
    onToggleFavourite: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(SgulaRadius.MdShape)
            .clickable(onClick = onClick)
            .padding(horizontal = SgulaSpacing.x2, vertical = SgulaSpacing.x3),
        horizontalArrangement = Arrangement.spacedBy(SgulaSpacing.x4),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TrackArt()
        Column(Modifier.weight(1f)) {
            Text(
                title,
                style = SgulaTextStyles.Body.copy(fontWeight = FontWeight.SemiBold),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(duration, style = SgulaTextStyles.BodySmall.copy(color = SgulaColors.TextTertiary))
        }
        IconToggle(
            iconRes = if (isFavourite) R.drawable.ic_star else R.drawable.ic_star_border,
            contentDescription = if (isFavourite) "Remove from favourites" else "Add to favourites",
            active = isFavourite,
            activeColor = SgulaColors.Accent,
            inactiveColor = SgulaPalette.WarmGray300,
            onClick = onToggleFavourite,
        )
    }
}

@Composable
fun TrackArt(modifier: Modifier = Modifier, dimension: Dp = 48.dp) {
    Box(
        modifier = modifier
            .size(dimension)
            .clip(SgulaRadius.SmShape)
            .drawBehind {
                // repeating diagonal stripes, stand in for real cover art
                val period = 12.dp.toPx() / 1.4142f
                drawRect(
                    Brush.linearGradient(
                        colorStops = arrayOf(
                            0.0f to SgulaPalette.Plum200,
                            0.5f to SgulaPalette.Plum200,
                            0.5f to SgulaPalette.Plum100,
                            1.0f to SgulaPalette.Plum100,
                        ),
                        start = Offset(0f, 0f),
                        end = Offset(period, period),
                        tileMode = TileMode.Repeated,
                    )
                )
            }
    )
}

@Composable
fun PlayerBar(
    title: String,
    subtitle: String,
    elapsed: String,
    total: String,
    progress: Float,
    isPlaying: Boolean,
    isLooping: Boolean,
    isFavourite: Boolean,
    onPlayPause: () -> Unit,
    onToggleLoop: () -> Unit,
    onToggleFavourite: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .sgulaShadow(SgulaElevation.Md, SgulaRadius.LgShape)
            .clip(SgulaRadius.LgShape)
            .background(SgulaColors.Surface)
            .padding(horizontal = SgulaSpacing.x5, vertical = SgulaSpacing.x4),
        verticalArrangement = Arrangement.spacedBy(SgulaSpacing.x3),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(SgulaSpacing.x4),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .background(SgulaColors.Primary)
                    .clickable(role = Role.Button, onClick = onPlayPause),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    painter = painterResource(
                        if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play
                    ),
                    contentDescription = if (isPlaying) "Pause" else "Play",
                    tint = Color.White,
                    modifier = Modifier.size(26.dp),
                )
            }

            Column(Modifier.weight(1f)) {
                Text(
                    title,
                    style = SgulaTextStyles.Body.copy(fontWeight = FontWeight.Bold),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(subtitle, style = SgulaTextStyles.BodySmall.copy(color = SgulaColors.TextTertiary))
            }

            IconToggle(
                iconRes = R.drawable.ic_repeat,
                contentDescription = "Loop track",
                active = isLooping,
                activeColor = SgulaPalette.Plum600,
                inactiveColor = SgulaColors.TextSecondary,
                onClick = onToggleLoop,
            )
            IconToggle(
                iconRes = if (isFavourite) R.drawable.ic_star else R.drawable.ic_star_border,
                contentDescription = if (isFavourite) "Remove from favourites" else "Add to favourites",
                active = isFavourite,
                activeColor = SgulaColors.Accent,
                inactiveColor = SgulaColors.TextSecondary,
                onClick = onToggleFavourite,
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(SgulaSpacing.x3),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                elapsed,
                style = SgulaTextStyles.Caption,
                modifier = Modifier.widthIn(min = 36.dp),
            )
            SeekBar(progress = progress, modifier = Modifier.weight(1f))
            Text(
                total,
                style = SgulaTextStyles.Caption,
                modifier = Modifier.widthIn(min = 36.dp),
            )
        }
    }
}

@Composable
fun SeekBar(progress: Float, modifier: Modifier = Modifier) {
    val clamped = progress.coerceIn(0f, 1f)

    BoxWithConstraints(
        modifier = modifier.height(20.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        val trackWidth = maxWidth

        Box(
            Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(CircleShape)
                .background(SgulaPalette.WarmGray200)
        ) {
            Box(
                Modifier
                    .fillMaxWidth(clamped)
                    .fillMaxHeight()
                    .clip(CircleShape)
                    .background(SgulaPalette.Plum500)
            )
        }

        // thumb. the white circle behind the small one gives it the ring look
        Box(
            modifier = Modifier
                .offset(x = (trackWidth * clamped) - 10.dp)
                .size(20.dp)
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center,
        ) {
            Box(Modifier.size(14.dp).clip(CircleShape).background(SgulaPalette.Plum600))
        }
    }
}

@Composable
private fun IconToggle(
    @DrawableRes iconRes: Int,
    contentDescription: String,
    active: Boolean,
    activeColor: Color,
    inactiveColor: Color,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(44.dp)
            .clip(CircleShape)
            .clickable(role = Role.Checkbox, onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = contentDescription,
            tint = if (active) activeColor else inactiveColor,
            modifier = Modifier.size(24.dp),
        )
    }
}

@Composable
fun SgulaTabs(
    tabs: List<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(SgulaRadius.MdShape)
            .background(SgulaColors.SurfaceSunken)
            .padding(SgulaSpacing.x1),
        horizontalArrangement = Arrangement.spacedBy(SgulaSpacing.x2),
    ) {
        tabs.forEachIndexed { index, tab ->
            val selected = index == selectedIndex
            Box(
                modifier = Modifier
                    .weight(1f)
                    .heightIn(min = 40.dp)
                    .then(
                        if (selected) Modifier.sgulaShadow(SgulaElevation.Sm, SgulaRadius.SmShape)
                        else Modifier
                    )
                    .clip(SgulaRadius.SmShape)
                    .background(if (selected) SgulaColors.Surface else Color.Transparent)
                    .clickable(role = Role.Tab) { onSelect(index) }
                    .padding(horizontal = SgulaSpacing.x3, vertical = 10.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    tab,
                    style = SgulaTextStyles.BodySmall.copy(
                        color = if (selected) SgulaPalette.Plum800 else SgulaColors.TextSecondary,
                        fontWeight = FontWeight.SemiBold,
                    ),
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
fun QuizOption(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 60.dp)
            .clip(SgulaRadius.MdShape)
            .background(if (selected) SgulaPalette.Plum50 else SgulaColors.Surface)
            .border(
                2.dp,
                if (selected) SgulaPalette.Plum500 else SgulaColors.Border,
                SgulaRadius.MdShape,
            )
            .clickable(role = Role.RadioButton, onClick = onClick)
            .padding(SgulaSpacing.x4),
        horizontalArrangement = Arrangement.spacedBy(SgulaSpacing.x3),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(22.dp)
                .clip(CircleShape)
                .background(if (selected) SgulaPalette.Plum500 else Color.Transparent)
                .border(
                    2.dp,
                    if (selected) SgulaPalette.Plum500 else SgulaPalette.WarmGray300,
                    CircleShape,
                ),
            contentAlignment = Alignment.Center,
        ) {
            if (selected) {
                Box(Modifier.size(10.dp).clip(CircleShape).background(SgulaPalette.Plum50))
            }
        }
        Text(
            text,
            style = SgulaTextStyles.Body.copy(fontWeight = FontWeight.SemiBold),
        )
    }
}

@Composable
fun QuizOptions(
    options: List<String>,
    selectedIndex: Int?,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SgulaSpacing.x3),
    ) {
        options.forEachIndexed { index, option ->
            QuizOption(
                text = option,
                selected = index == selectedIndex,
                onClick = { onSelect(index) },
            )
        }
    }
}

@Composable
fun SgulaModal(
    title: String,
    body: String,
    confirmText: String,
    onConfirm: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    dismissText: String? = null,
    onDismissAction: (() -> Unit)? = null,
    confirmVariant: ButtonVariant = ButtonVariant.Primary,
    icon: String? = null,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(SgulaColors.Scrim)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onDismissRequest,
                )
                .padding(SgulaSpacing.x4),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = modifier
                    .widthIn(max = 420.dp)
                    .fillMaxWidth()
                    .sgulaShadow(SgulaElevation.Lg, SgulaRadius.LgShape)
                    .clip(SgulaRadius.LgShape)
                    .background(SgulaColors.Surface)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {}, // eats the tap so tapping the box doesn't close it
                    )
                    .padding(SgulaSpacing.x6),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(SgulaPalette.Plum100),
                    contentAlignment = Alignment.Center,
                ) {
                    if (icon != null) {
                        Text(icon, style = SgulaTextStyles.H1.copy(color = SgulaPalette.Plum600))
                    }
                }
                Spacer(Modifier.height(SgulaSpacing.x4))

                Text(title, style = SgulaTextStyles.H2, textAlign = TextAlign.Center)
                Spacer(Modifier.height(SgulaSpacing.x2))
                Text(body, style = SgulaTextStyles.BodySecondary, textAlign = TextAlign.Center)
                Spacer(Modifier.height(SgulaSpacing.x5))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(SgulaSpacing.x3),
                ) {
                    if (dismissText != null) {
                        SgulaButton(
                            text = dismissText,
                            onClick = onDismissAction ?: onDismissRequest,
                            variant = ButtonVariant.Secondary,
                            modifier = Modifier.weight(1f),
                            fullWidth = true,
                        )
                    }
                    SgulaButton(
                        text = confirmText,
                        onClick = onConfirm,
                        variant = confirmVariant,
                        modifier = Modifier.weight(1f),
                        fullWidth = true,
                    )
                }
            }
        }
    }
}

@Composable
fun AdminTable(
    headers: List<Pair<String, Float>>,
    modifier: Modifier = Modifier,
    rows: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .sgulaShadow(SgulaElevation.Sm, SgulaRadius.LgShape)
            .clip(SgulaRadius.LgShape)
            .background(SgulaColors.Surface),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(SgulaColors.SurfaceSunken)
                .padding(horizontal = SgulaSpacing.x4, vertical = SgulaSpacing.x3),
        ) {
            headers.forEach { (label, weight) ->
                Text(
                    label.uppercase(),
                    style = SgulaTextStyles.Caption.copy(letterSpacing = 0.52.sp),
                    modifier = Modifier.weight(weight),
                )
            }
        }
        rows()
    }
}

@Composable
fun AdminRow(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    Column(modifier.fillMaxWidth()) {
        Box(Modifier.fillMaxWidth().height(1.dp).background(SgulaColors.Border))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(SgulaSpacing.x4),
            verticalAlignment = Alignment.CenterVertically,
            content = content,
        )
    }
}

@Composable
fun RowScope.AdminCell(
    weight: Float,
    content: @Composable () -> Unit,
) {
    Box(Modifier.weight(weight), contentAlignment = Alignment.CenterStart) { content() }
}

enum class AccountStatus { Active, Deactivated }

@Composable
fun StatusBadge(status: AccountStatus, modifier: Modifier = Modifier) {
    val bg = if (status == AccountStatus.Active) SgulaPalette.Plum100 else SgulaPalette.WarmGray100
    val fg = if (status == AccountStatus.Active) SgulaPalette.Plum700 else SgulaPalette.Ink500
    val dot = if (status == AccountStatus.Active) SgulaPalette.Plum500 else SgulaPalette.WarmGray400

    Row(
        modifier = modifier
            .clip(SgulaRadius.SmShape)
            .background(bg)
            .padding(horizontal = 10.dp, vertical = 5.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(Modifier.size(7.dp).clip(CircleShape).background(dot))
        Text(
            status.name,
            style = SgulaTextStyles.Caption.copy(color = fg, fontWeight = FontWeight.Bold),
        )
    }
}
