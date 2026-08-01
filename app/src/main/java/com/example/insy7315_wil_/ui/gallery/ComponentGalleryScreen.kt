package com.example.insy7315_wil_.ui.gallery

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.insy7315_wil_.ui.components.AccountStatus
import com.example.insy7315_wil_.ui.components.AdminCell
import com.example.insy7315_wil_.ui.components.AdminRow
import com.example.insy7315_wil_.ui.components.AdminTable
import com.example.insy7315_wil_.ui.components.AffirmationCard
import com.example.insy7315_wil_.ui.components.ButtonSize
import com.example.insy7315_wil_.ui.components.ButtonVariant
import com.example.insy7315_wil_.ui.components.CardBody
import com.example.insy7315_wil_.ui.components.CardTitle
import com.example.insy7315_wil_.ui.components.MoodScale
import com.example.insy7315_wil_.ui.components.NavItem
import com.example.insy7315_wil_.ui.components.PageHeader
import com.example.insy7315_wil_.ui.components.PasswordStrength
import com.example.insy7315_wil_.ui.components.PasswordStrengthMeter
import com.example.insy7315_wil_.ui.components.PlantStage
import com.example.insy7315_wil_.ui.components.PlantStages
import com.example.insy7315_wil_.ui.components.PlayerBar
import com.example.insy7315_wil_.ui.components.PointsToast
import com.example.insy7315_wil_.ui.components.ProgressRing
import com.example.insy7315_wil_.ui.components.QuizOptions
import com.example.insy7315_wil_.ui.components.SgulaButton
import com.example.insy7315_wil_.ui.components.SgulaCard
import com.example.insy7315_wil_.ui.components.SgulaDropdownField
import com.example.insy7315_wil_.ui.components.SgulaModal
import com.example.insy7315_wil_.ui.components.SgulaScreen
import com.example.insy7315_wil_.ui.components.SgulaTabs
import com.example.insy7315_wil_.ui.components.SgulaTextArea
import com.example.insy7315_wil_.ui.components.SgulaTextField
import com.example.insy7315_wil_.ui.components.StatusBadge
import com.example.insy7315_wil_.ui.components.StreakChip
import com.example.insy7315_wil_.ui.components.TrackItem
import com.example.insy7315_wil_.ui.theme.SgulaColors
import com.example.insy7315_wil_.ui.theme.SgulaSpacing
import com.example.insy7315_wil_.ui.theme.SgulaTextStyles
import com.example.insy7315_wil_.ui.theme.SgulaTheme

// demo screen so we can see all the components on a phone, not part of the app
@Composable
fun ComponentGalleryScreen() {
    var mood by remember { mutableStateOf<Int?>(2) }
    var tab by remember { mutableIntStateOf(1) }
    var quiz by remember { mutableStateOf<Int?>(1) }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("jane@invalid") }
    var journal by remember { mutableStateOf("") }
    var checkInTime by remember { mutableStateOf("Morning") }
    var favourited by remember { mutableStateOf(true) }
    var playing by remember { mutableStateOf(true) }
    var showModal by remember { mutableStateOf(false) }
    var route by remember { mutableStateOf("home") }

    SgulaScreen(
        navItems = GalleryNavItems,
        selectedRoute = route,
        onNavigate = { route = it },
    ) {
        PageHeader(
            title = "Sgula components",
            subtitle = "Build every screen from these. Don't invent new ones.",
        )

        Section("Buttons", "Primary for the main action. Destructive only for irreversible actions.") {
            Row(horizontalArrangement = Arrangement.spacedBy(SgulaSpacing.x3)) {
                SgulaButton("Save entry", onClick = {})
                SgulaButton("Not now", onClick = {}, variant = ButtonVariant.Secondary)
            }
            Spacer(Modifier.height(SgulaSpacing.x3))
            Row(horizontalArrangement = Arrangement.spacedBy(SgulaSpacing.x3)) {
                SgulaButton("Learn more", onClick = {}, variant = ButtonVariant.Ghost)
                SgulaButton("Delete", onClick = {}, variant = ButtonVariant.Destructive)
                SgulaButton("Message", onClick = {}, variant = ButtonVariant.Ghost, size = ButtonSize.Small)
            }
            Spacer(Modifier.height(SgulaSpacing.x3))
            SgulaButton("Saving…", onClick = {}, enabled = false, fullWidth = true)
            Spacer(Modifier.height(SgulaSpacing.x3))
            SgulaButton("Start today's meditation", onClick = {}, fullWidth = true)
        }

        Section("Cards", "The affirmation card is reserved for Home.") {
            SgulaCard {
                CardTitle("Today's check-in")
                CardBody("You logged a calm mood this morning. Keep noticing what helps.")
            }
            Spacer(Modifier.height(SgulaSpacing.x4))
            AffirmationCard(affirmation = "\"I am allowed to grow at my own pace.\"")
        }

        Section("Form elements", "Every input needs a visible label.") {
            SgulaTextField(name, { name = it }, label = "Full name", placeholder = "Jane Doe")
            SgulaTextField(email, { email = it }, label = "Email", error = "Enter a valid email address.")
            SgulaTextArea(journal, { journal = it }, label = "Journal entry", optional = true, placeholder = "What's on your mind today?")
            SgulaDropdownField(
                label = "Preferred check-in time",
                options = listOf("Morning", "Afternoon", "Evening"),
                selected = checkInTime,
                onSelect = { checkInTime = it },
            )
            PasswordStrengthMeter(PasswordStrength.Medium)
        }

        Section("Mood selector", "Abstract tone dots — no emoji or faces, on purpose.") {
            MoodScale(selectedIndex = mood, onSelect = { mood = it })
        }

        Section("Points toast & streak chip", "Shown after a saved action.") {
            Row(
                horizontalArrangement = Arrangement.spacedBy(SgulaSpacing.x3),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                PointsToast(points = 5)
                StreakChip(days = 12)
            }
        }

        Section("Progress ring", "Daily goal on Home. Takes a Float 0f..1f.") {
            ProgressRing(progress = 0.7f)
        }

        Section("Plant stages", "Jane is at Sprout. Wilted state shown second.") {
            PlantStages(currentStage = PlantStage.Sprout)
            Spacer(Modifier.height(SgulaSpacing.x4))
            PlantStages(currentStage = PlantStage.Sprout, wilted = true)
        }

        Section("Audio", "Track rows and the player bar.") {
            TrackItem(
                title = "Rain on Leaves",
                duration = "14:00",
                isFavourite = favourited,
                onToggleFavourite = { favourited = !favourited },
                onClick = {},
            )
            TrackItem(
                title = "Ocean Drift",
                duration = "20:00",
                isFavourite = false,
                onToggleFavourite = {},
                onClick = {},
            )
            Spacer(Modifier.height(SgulaSpacing.x4))
            PlayerBar(
                title = "Rain on Leaves",
                subtitle = "Nature sounds",
                elapsed = "4:12",
                total = "14:00",
                progress = 0.3f,
                isPlaying = playing,
                isLooping = true,
                isFavourite = true,
                onPlayPause = { playing = !playing },
                onToggleLoop = {},
                onToggleFavourite = {},
            )
        }

        Section("Tabs", "Switches between audio categories.") {
            SgulaTabs(
                tabs = listOf("White Noise", "Nature", "Guided"),
                selectedIndex = tab,
                onSelect = { tab = it },
            )
        }

        Section("Quiz options", "Large tappable answers for the meditation quiz.") {
            QuizOptions(
                options = listOf(
                    "I want to release tension in my body",
                    "I want to quiet racing thoughts",
                    "I want help falling asleep",
                ),
                selectedIndex = quiz,
                onSelect = { quiz = it },
            )
        }

        Section("Modal", "Overlay + centred dialog.") {
            SgulaButton("Show delete confirmation", onClick = { showModal = true }, variant = ButtonVariant.Secondary)
        }

        Section("Admin table", "Therapist views — client lists, broadcasts.") {
            AdminTable(
                headers = listOf("Client" to 2f, "Streak" to 1.2f, "Status" to 1.5f),
            ) {
                AdminRow {
                    AdminCell(2f) { Text("Jane Doe", style = SgulaTextStyles.Body) }
                    AdminCell(1.2f) { Text("12 days", style = SgulaTextStyles.Body) }
                    AdminCell(1.5f) { StatusBadge(AccountStatus.Active) }
                }
                AdminRow {
                    AdminCell(2f) { Text("Sam Rivera", style = SgulaTextStyles.Body) }
                    AdminCell(1.2f) { Text("0 days", style = SgulaTextStyles.Body) }
                    AdminCell(1.5f) { StatusBadge(AccountStatus.Deactivated) }
                }
            }
        }
    }

    if (showModal) {
        SgulaModal(
            title = "Delete this entry?",
            body = "This journal entry will be permanently removed. This can't be undone.",
            confirmText = "Delete",
            confirmVariant = ButtonVariant.Destructive,
            dismissText = "Cancel",
            onConfirm = { showModal = false },
            onDismissRequest = { showModal = false },
        )
    }
}

private val GalleryNavItems = listOf(
    NavItem("home", "Home"),
    NavItem("mood", "Mood"),
    NavItem("journal", "Journal"),
    NavItem("audio", "Audio"),
    NavItem("profile", "Profile"),
)

@Composable
private fun Section(
    title: String,
    note: String,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth().padding(bottom = SgulaSpacing.x7)) {
        Text(title, style = SgulaTextStyles.H2)
        Text(
            note,
            style = SgulaTextStyles.BodySmall.copy(color = SgulaColors.TextSecondary),
            modifier = Modifier.padding(top = SgulaSpacing.x1, bottom = SgulaSpacing.x4),
        )
        content()
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 1400)
@Composable
private fun ComponentGalleryPreview() {
    SgulaTheme { ComponentGalleryScreen() }
}
