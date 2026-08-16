package com.example.insy7315_wil_.ui.screens.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.insy7315_wil_.R
import com.example.insy7315_wil_.data.SessionManager
import com.example.insy7315_wil_.databinding.FragmentRegisterBinding
import com.example.insy7315_wil_.ui.widget.SgulaPasswordStrengthView

class RegisterFragment : Fragment(R.layout.fragment_register) {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRegisterBinding.bind(view)
        binding.registerPassword.doOnTextChanged { password ->
            binding.registerPasswordStrength.strength = when {
                password.length >= 10 && password.any(Char::isDigit) -> SgulaPasswordStrengthView.Strength.STRONG
                password.length >= 6 -> SgulaPasswordStrengthView.Strength.MEDIUM
                else -> SgulaPasswordStrengthView.Strength.WEAK
            }
        }
        binding.registerButton.setOnClickListener {
            binding.registerName.error = if (binding.registerName.text.isBlank()) "Enter your name" else null
            binding.registerEmail.error = if (binding.registerEmail.text.contains("@")) null else "Enter a valid email address"
            binding.registerPassword.error = if (binding.registerPassword.text.length >= 6) null else "Use at least 6 characters"
            if (binding.registerName.error == null && binding.registerEmail.error == null && binding.registerPassword.error == null) {
                SessionManager(requireContext()).signIn(binding.registerEmail.text.trim(), binding.registerName.text.trim())
                findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
            }
        }
        binding.registerLogin.setOnClickListener { findNavController().navigate(R.id.action_registerFragment_to_loginFragment) }
    }

    override fun onDestroyView() { _binding = null; super.onDestroyView() }
}
