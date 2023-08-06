package com.example.shapovalov.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.shapovalov.InputErrorType
import com.example.shapovalov.ErrorType
import com.example.shapovalov.authentication.R
import com.example.shapovalov.authentication.databinding.FragmentLoginBinding
import com.example.shapovalov.presentation.login.LoginState
import com.example.shapovalov.presentation.login.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding: FragmentLoginBinding by viewBinding()

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.openRegisterPageLinearLayout.setOnClickListener {
            viewModel.navigateToRegistration()
        }
        binding.signInButton.setOnClickListener {
            viewModel.login(
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }
    }

    private fun initObservers() {
        viewModel.state.observe(viewLifecycleOwner, ::applyState)
        viewModel.showInputError.observe(viewLifecycleOwner, ::showInputError)
        viewModel.showResponseError.observe(viewLifecycleOwner, ::showResponseError)
    }

    private fun applyState(state: LoginState) {
        when (state) {
            is LoginState.Initial -> applyInitialState()

            is LoginState.Loading -> applyLoadingState()

            is LoginState.Error -> applyErrorState(state)
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

    private fun showResponseError(error: ErrorType) {
        val message = error.getErrorMessage(requireContext())
        val snackbar = Snackbar.make(binding.root, message, Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("OK") { snackbar.dismiss() }.show()
    }

    private fun applyInitialState() {
        with(binding) {
            loginInputLayout.error = null
            passwordInputLayout.error = null
            progressBar.isGone = true
            greetingTextView.isVisible = true
            loginInputLayout.isVisible = true
            passwordInputLayout.isVisible = true
            signInButton.isVisible = true
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
            signInButton.isVisible = false
            openRegisterPageLinearLayout.isVisible = false
            decorationImageView.isVisible = false
            errorLayout.root.isGone = true
        }
    }

    private fun applyErrorState(state: LoginState.Error) {
        with(binding) {
            progressBar.isGone = true
            greetingTextView.isVisible = false
            loginInputLayout.isVisible = false
            passwordInputLayout.isVisible = false
            signInButton.isVisible = false
            openRegisterPageLinearLayout.isVisible = false
            decorationImageView.isVisible = false
            errorLayout.root.isVisible = true
            errorLayout.errorBodyTextView.text = state.error.getErrorMessage(requireContext())
            errorLayout.reconnectButton.setOnClickListener { viewModel.restoreInitialState() }
        }
    }
}