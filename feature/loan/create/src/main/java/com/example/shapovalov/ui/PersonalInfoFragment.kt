package com.example.shapovalov.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.shapovalov.InputErrorType
import com.example.shapovalov.domain.entity.LoanConditions
import com.example.shapovalov.domain.entity.LoanCreation
import com.example.shapovalov.feature.loan.create.R
import com.example.shapovalov.feature.loan.create.databinding.FragmentPersonalInfoBinding
import com.example.shapovalov.getParcelableCompat
import com.example.shapovalov.presentation.personalinfo.PersonalInfoState
import com.example.shapovalov.presentation.personalinfo.PersonalInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val CONDITIONS = "CONDITIONS"
private const val AMOUNT = "AMOUNT"
private var Bundle.conditions
    get() = getParcelableCompat<LoanConditions>(CONDITIONS)
    set(value) = putParcelable(CONDITIONS, value)

private var Bundle.amount
    get() = getInt(AMOUNT)
    set(value) = putInt(AMOUNT, value)

@AndroidEntryPoint
class PersonalInfoFragment : Fragment(R.layout.fragment_personal_info) {
    companion object {
        fun newInstance(conditions: LoanConditions, amount: Int) = PersonalInfoFragment().apply {

            arguments = Bundle().apply {
                this.conditions = conditions
                this.amount = amount
            }
        }
    }

    private val binding: FragmentPersonalInfoBinding by viewBinding()

    private val viewModel: PersonalInfoViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.arrangeLoanButton.setOnClickListener {}
        binding.topAppBar.setNavigationOnClickListener { viewModel.exitScreen() }
        binding.arrangeLoanButton.setOnClickListener { viewModel.createLoan(getLoanCreationValues()) }
    }

    private fun initObservers() {
        viewModel.state.observe(viewLifecycleOwner, ::applyState)
        viewModel.showFirstNameError.observe(viewLifecycleOwner, ::showFirstNameError)
        viewModel.showLastNameError.observe(viewLifecycleOwner, ::showSecondNameError)
        viewModel.showPhoneError.observe(viewLifecycleOwner, ::showPhoneError)
    }

    private fun showFirstNameError(error: InputErrorType) {
        val message = error.getErrorMessage(requireContext())
        binding.nameEditText.error = message

    }

    private fun showSecondNameError(error: InputErrorType) {
        val message = error.getErrorMessage(requireContext())
        binding.lastnameNameEditText.error = message
    }

    private fun showPhoneError(error: InputErrorType) {
        val message = error.getErrorMessage(requireContext())
        binding.phoneNumberEditText.error = message
    }

    private fun applyState(state: PersonalInfoState) {
        when (state) {
            is PersonalInfoState.Initial -> applyInitialState()

            is PersonalInfoState.Loading -> applyLoadingState()

            is PersonalInfoState.Error -> applyErrorState(state)

        }
    }

    private fun applyInitialState() {
        with(binding) {
            personalInfoLayout.isVisible = true
            arrangeLoanButton.isVisible = true
            progressBar.isVisible = false
            errorLayout.root.isVisible = false
        }
    }

    private fun applyLoadingState() {
        with(binding) {
            personalInfoLayout.isVisible = false
            progressBar.isVisible = false
            arrangeLoanButton.isVisible = false
            errorLayout.root.isVisible = false
        }
    }

    private fun applyErrorState(state: PersonalInfoState.Error) {
        with(binding) {
            personalInfoLayout.isVisible = true
            arrangeLoanButton.isVisible = true
            progressBar.isVisible = false
            errorLayout.root.isVisible = true
        }
    }

    private fun getLoanCreationValues(): LoanCreation {
        val amount = arguments?.amount ?: 0
        val period = arguments?.conditions?.period ?: 0
        val percent = arguments?.conditions?.percent ?: 0.0

        return LoanCreation(
            amount = amount,
            firstName = binding.nameEditText.text.toString(),
            lastName = binding.lastnameNameEditText.text.toString(),
            percent = percent,
            period = period,
            phoneNumber = binding.phoneNumberEditText.text.toString()
        )
    }
}