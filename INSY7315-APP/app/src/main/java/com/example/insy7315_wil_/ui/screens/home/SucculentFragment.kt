package com.example.insy7315_wil_.ui.screens.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.insy7315_wil_.R
import com.example.insy7315_wil_.ui.screens.redirectGuestFromMemberContent

class SucculentFragment : Fragment(R.layout.fragment_succulent) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        redirectGuestFromMemberContent()
    }
}
