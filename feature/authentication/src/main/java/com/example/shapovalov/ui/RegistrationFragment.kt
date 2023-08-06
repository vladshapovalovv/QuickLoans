package com.example.shapovalov.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.shapovalov.InputErrorType
import com.example.shapovalov.ErrorType
import com.example.shapovalov.authentication.R
import com.example.shapovalov.authentication.databinding.FragmentRegistrationBinding
import com.example.shapovalov.presentation.registration.RegistrationState
import com.example.shapovalov.presentation.registration.RegistrationViewModel
import com.example.shapovalov.subscribeSafe
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private val binding: FragmentRegistrationBinding by viewBinding()

    private val viewModel: RegistrationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.openRegisterPageLinearLayout.setOnClickListener { viewModel.navigateToLogin() }
        binding.signUpButton.setOnClickListener {
            viewModel.register(
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }
    }

    private fun initObservers() {
        viewModel.state.observe(viewLifecycleOwner, ::applyState)
        viewModel.showInputError.observe(viewLifecycleOwner, ::showInputError)
        viewModel.showResponseError.observe(viewLifecycleOwner, ::showResponseError)
        viewModel.displayRegistrationSuccess.subscribeSafe(
            viewLifecycleOwner,
            ::displayRegistrationSuccess
        )
    }

    private fun applyState(state: RegistrationState) {
        when (state) {
            is RegistrationState.Initial -> applyInitialState()
            is RegistrationState.Loading -> applyLoadingState()
            is RegistrationState.Error -> applyErrorState(state)
        }
    }

    private fun showInputError(error: InputErrorType) {
        val message = error.getErrorMessage(requireContext())
        when (error) {
            InputErrorType.NAME_EMPTY -> binding.loginInputLayout.error = message
            InputErrorType.PASSWORD_EMPTY -> binding.passwordInputLayout.error = message
            InputErrorType.FIELD_EMPTY -> binding.passwordInputLayout.error = message
        }
    }

    private fun displayRegistrationSuccess() {
        Toast.makeText(
            requireContext(),
            getString(R.string.sign_up_successfully_message),
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showResponseError(error: ErrorType) {
        val message = error.getErrorMessage(requireContext())
        val snackbar = Snackbar.make(binding.root, message, Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction(R.string.action_ok) { snackbar.dismiss() }.show()
    }

    private fun applyInitialState() {
        with(binding) {
            loginInputLayout.error = null
            passwordInputLayout.error = null
            progressBar.isVisible = false
            greetingTextView.isVisible = true
            loginInputLayout.isVisible = true
            passwordInputLayout.isVisible = true
            signUpButton.isVisible = true
            openRegisterPageLinearLayout.isVisible = true
            decorationImageView.isVisible = true
            errorLayout.root.isGone = true
        }
    }

    private fun applyLoadingState() {
        with(binding) {
            binding.progressBar.isVisible = true
            greetingTextView.isVisible = false
            loginInputLayout.isVisible = false
            passwordInputLayout.isVisible = false
            signUpButton.isVisible = false
            openRegisterPageLinearLayout.isVisible = false
            decorationImageView.isVisible = false
            errorLayout.root.isGone = true
        }
    }

    private fun applyErrorState(state: RegistrationState.Error) {
        with(binding) {
            progressBar.isVisible = false
            greetingTextView.isVisible = true
            loginInputLayout.isVisible = true
            passwordInputLayout.isVisible = true
            signUpButton.isVisible = true
            openRegisterPageLinearLayout.isVisible = true
            decorationImageView.isVisible = true
            errorLayout.root.isVisible = true
            errorLayout.errorBodyTextView.text = state.error.getErrorMessage(requireContext())
            errorLayout.reconnectButton.setOnClickListener { viewModel.restoreInitialState() }
        }
    }
}