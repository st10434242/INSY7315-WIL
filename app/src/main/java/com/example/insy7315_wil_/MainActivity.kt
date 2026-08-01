package com.example.insy7315_wil_

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.insy7315_wil_.ui.gallery.ComponentGalleryScreen
import com.example.insy7315_wil_.ui.theme.SgulaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SgulaTheme {
                // change this to the real start screen once we have one
                ComponentGalleryScreen()
            }
        }
    }
}
