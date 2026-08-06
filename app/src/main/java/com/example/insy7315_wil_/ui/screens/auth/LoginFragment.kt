package com.example.insy7315_wil_.ui.screens.auth

import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.insy7315_wil_.R
import com.example.insy7315_wil_.data.SessionManager
import com.example.insy7315_wil_.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)
        binding.loginButton.setOnClickListener {
            val email = binding.loginEmail.text.trim()
            val password = binding.loginPassword.text
            binding.loginEmail.error = if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) null else "Enter a valid email address"
            binding.loginPassword.error = if (password.isNotBlank()) null else "Enter your password"
            if (binding.loginEmail.error == null && binding.loginPassword.error == null) {
                SessionManager(requireContext()).signIn(email)
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }
        binding.loginForgotPassword.setOnClickListener { findNavController().navigate(R.id.action_loginFragment_to_passwordResetFragment) }
        binding.loginGuestButton.setOnClickListener {
            SessionManager(requireContext()).continueAsGuest()
            findNavController().navigate(R.id.action_loginFragment_to_guestLandingFragment)
        }
        binding.loginRegister.setOnClickListener { findNavController().navigate(R.id.action_loginFragment_to_registerFragment) }
    }

    override fun onDestroyView() { _binding = null; super.onDestroyView() }
}
