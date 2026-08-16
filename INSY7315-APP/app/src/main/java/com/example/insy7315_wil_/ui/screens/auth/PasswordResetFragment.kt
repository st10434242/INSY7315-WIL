package com.example.insy7315_wil_.ui.screens.auth

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.insy7315_wil_.R
import com.example.insy7315_wil_.databinding.FragmentPasswordResetBinding

class PasswordResetFragment : Fragment(R.layout.fragment_password_reset) {
    private var _binding: FragmentPasswordResetBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPasswordResetBinding.bind(view)
        binding.passwordResetSend.setOnClickListener {
            if (!binding.passwordResetEmail.text.contains("@")) {
                binding.passwordResetEmail.error = "Enter a valid email address"
            } else {
                binding.passwordResetEmail.error = null
                binding.passwordResetForm.isVisible = false
                binding.passwordResetConfirmation.isVisible = true
            }
        }
        binding.passwordResetLogin.setOnClickListener { findNavController().navigate(R.id.action_passwordResetFragment_to_loginFragment) }
    }

    override fun onDestroyView() { _binding = null; super.onDestroyView() }
}
