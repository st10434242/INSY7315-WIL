package com.example.insy7315_wil_

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.example.insy7315_wil_.ui.widget.NavItem
import com.example.insy7315_wil_.ui.widget.SgulaModal
import com.example.insy7315_wil_.ui.widget.SgulaTabBarView
import com.example.insy7315_wil_.ui.widget.SgulaTopNavView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.sgula_screen)

        val content = findViewById<FrameLayout>(R.id.sgula_content)
        val gallery = LayoutInflater.from(this)
            .inflate(R.layout.content_gallery, content, true)

        // only one of these exists, the w1024dp layout swaps the tab bar for the top nav
        findViewById<SgulaTabBarView>(R.id.sgula_tab_bar)?.apply {
            setItems(GalleryNavItems, "home")
            onNavigate = { selectedRoute = it }
        }
        findViewById<SgulaTopNavView>(R.id.sgula_top_nav)?.apply {
            setBrand(getString(R.string.sgula_brand))
            setItems(GalleryNavItems, "home")
            onNavigate = { selectedRoute = it }
        }

        gallery.findViewById<MaterialButton>(R.id.gallery_show_modal).setOnClickListener {
            SgulaModal.show(
                context = this,
                title = getString(R.string.sgula_demo_modal_title),
                body = getString(R.string.sgula_demo_modal_body),
                confirmText = getString(R.string.sgula_delete),
                onConfirm = {},
                dismissText = getString(R.string.sgula_cancel),
                destructive = true,
            )
        }
    }

    private companion object {
        val GalleryNavItems = listOf(
            NavItem("home", "Home"),
            NavItem("mood", "Mood"),
            NavItem("journal", "Journal"),
            NavItem("audio", "Audio"),
            NavItem("profile", "Profile"),
        )
    }
}
