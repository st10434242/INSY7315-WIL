package com.example.insy7315_wil_.ui.screens.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.insy7315_wil_.R
import com.example.insy7315_wil_.data.SessionManager
import com.example.insy7315_wil_.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSettingsBinding.bind(view)
        val session = SessionManager(requireContext())
        binding.settingsName.text = session.displayName
        binding.settingsEmail.text = session.email
        binding.settingsAnonymousInsights.isChecked = session.sharesAnonymousInsights
        binding.settingsReminders.isChecked = session.remindersEnabled
        binding.settingsProgress.isChecked = session.activityProgressVisible
        binding.settingsAnonymousInsights.setOnCheckedChangeListener { _, checked -> session.sharesAnonymousInsights = checked }
        binding.settingsReminders.setOnCheckedChangeListener { _, checked -> session.remindersEnabled = checked }
        binding.settingsProgress.setOnCheckedChangeListener { _, checked -> session.activityProgressVisible = checked }
        binding.settingsLogout.setOnClickListener {
            session.logout()
            findNavController().navigate(R.id.action_settingsFragment_to_launchFragment)
        }
    }

    override fun onDestroyView() { _binding = null; super.onDestroyView() }
}
