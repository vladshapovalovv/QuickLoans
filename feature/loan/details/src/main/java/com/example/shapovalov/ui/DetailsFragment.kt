package com.example.shapovalov.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.shapovalov.ErrorType
import com.example.shapovalov.domain.entity.Loan
import com.example.shapovalov.feature.loan.details.R
import com.example.shapovalov.feature.loan.details.databinding.FragmentDetailsBinding
import com.example.shapovalov.formatAmountText
import com.example.shapovalov.formatAsPercentage
import com.example.shapovalov.formatDateTime
import com.example.shapovalov.presentation.DetailsState
import com.example.shapovalov.presentation.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val LOAN_ID = "LOAN_ID"
private var Bundle.loanId
    get() = getInt(LOAN_ID)
    set(value) = putInt(LOAN_ID, value)

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    companion object {
        fun newInstance(loanId: Int) = DetailsFragment().apply {
            arguments = Bundle().apply {
                this.loanId = loanId
            }
        }
    }

    private val viewModel: DetailsViewModel by viewModels()

    private val binding: FragmentDetailsBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getLoanDetails()
        initListeners()
        initObservers()
    }

    private fun getLoanDetails() {
        viewModel.getDetails(arguments?.loanId ?: 0)
    }

    private fun initListeners() {
        binding.topAppBar.setNavigationOnClickListener { viewModel.closeDetails() }
    }

    private fun initObservers() {
        viewModel.state.observe(viewLifecycleOwner, ::applyState)
    }

    private fun applyState(state: DetailsState) {
        when (state) {
            is DetailsState.Loading, DetailsState.Initial -> applyLoadingState()

            is DetailsState.Content -> applyContentState(state.loan)

            is DetailsState.Error -> applyErrorState(state.error)
        }
    }

    private fun applyLoadingState() {
        binding.loanInfoCardView.isVisible = false
        binding.instructionMaterialCard.isVisible = false
        binding.progressBar.isVisible = true
        binding.errorLayout.root.isVisible = false
    }

    private fun applyContentState(loan: Loan) {

        binding.loanInfoCardView.isVisible = true
        binding.instructionMaterialCard.isVisible = true
        binding.progressBar.isVisible = false
        binding.errorLayout.root.isVisible = false

        with(binding) {
            statusTextView.text = formatLoanStatus(requireContext(), loan.status)
            amountTextView.text = formatAmountText(requireContext(), loan.amount)
            dateTimeTextView.text = formatDateTime(loan.date)
            nameTextView.text = buildFullNameString(loan.firstName, loan.lastName)
            percentTextView.text = formatAsPercentage(loan.percent)
            periodTextView.text = buildPeriodString(loan.period)
            phoneNumberTextView.text = loan.phoneNumber
        }
    }

    private fun applyErrorState(error: ErrorType) {
        binding.loanInfoCardView.isVisible = false
        binding.instructionMaterialCard.isVisible = false
        binding.progressBar.isVisible = false
        binding.errorLayout.root.isVisible = true
        binding.errorLayout.errorBodyTextView.text = error.getErrorMessage(requireContext())
        binding.errorLayout.reconnectButton.setOnClickListener { getLoanDetails() }
    }

    private fun buildFullNameString(firstName: String, lastName: String): String {
        return buildString {
            append(firstName)
            append(getString(com.example.shapovalov.component.ui.R.string.space))
            append(lastName)
        }
    }

    private fun buildPeriodString(period: Int): String {
        return buildString {
            append(period)
            append(getString(com.example.shapovalov.component.ui.R.string.period_days))
        }
    }

}