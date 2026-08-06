package com.example.insy7315_wil_.ui.screens.journal

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.insy7315_wil_.R
import com.example.insy7315_wil_.ui.screens.redirectGuestFromMemberContent

class JournalHistoryFragment : Fragment(R.layout.fragment_journal_history) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        redirectGuestFromMemberContent()
    }
}
