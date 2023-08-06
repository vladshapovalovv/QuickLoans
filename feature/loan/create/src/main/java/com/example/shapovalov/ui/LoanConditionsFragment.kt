package com.example.shapovalov.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.shapovalov.ErrorType
import com.example.shapovalov.calculateWithPercentage
import com.example.shapovalov.domain.entity.LoanConditions
import com.example.shapovalov.feature.loan.create.R
import com.example.shapovalov.feature.loan.create.databinding.FragmentLoanConditionsBinding
import com.example.shapovalov.formatAmountText
import com.example.shapovalov.formatAsPercentage
import com.example.shapovalov.presentation.condition.LoanConditionsState
import com.example.shapovalov.presentation.condition.LoanConditionsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

@AndroidEntryPoint
class LoanConditionsFragment : Fragment(R.layout.fragment_loan_conditions) {

    private val viewModel: LoanConditionsViewModel by viewModels()

    private val binding: FragmentLoanConditionsBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initObservers()

    }
    private fun initListeners() {
        binding.topAppBar.setNavigationOnClickListener { viewModel.exitScreen() }

    }

    private fun initObservers(){
        viewModel.state.observe(viewLifecycleOwner, ::applyState)
    }

    private fun applyState(state: LoanConditionsState){
        when (state) {
            LoanConditionsState.Initial,
            LoanConditionsState.Loading    -> applyLoadingState()

            is LoanConditionsState.Content -> applyContentState(state.conditions)


            is LoanConditionsState.Error -> applyErrorState(state.error)
        }
    }

    private fun applyLoadingState() {
        setViewsVisibility(false)
        binding.progressBar.isVisible = true
        binding.errorLayout.root.isVisible = false
    }

    private fun applyContentState(loanConditions: LoanConditions) {
        setViewsVisibility(true)
        binding.progressBar.isGone = true
        binding.errorLayout.root.isVisible = false
        binding.percentTextview.text = formatAsPercentage(loanConditions.percent)
        binding.periodTextView.text = buildString {
            append(loanConditions.period)
            append(getString(R.string.days))
        }
        binding.maxValueTextView.text = formatAmountText(requireContext(), loanConditions.maxAmount)
        binding.amountSlider.valueTo = loanConditions.maxAmount.toFloat()
        monitorReturnAmount(loanConditions)
        binding.continueButton.setOnClickListener {
            viewModel.navigateToPersonalInfo(
                loanConditions,
                binding.amountSlider.value.roundToInt()
            )
        }
    }
    private fun applyErrorState(errorType: ErrorType) {
        setViewsVisibility(false)
        binding.progressBar.isVisible = false
        binding.errorLayout.root.isVisible = true
        binding.errorLayout.errorBodyTextView.text = errorType.getErrorMessage(requireContext())
        binding.errorLayout.reconnectButton.setOnClickListener { viewModel.getLoanConditions() }
    }

    private fun monitorReturnAmount(conditions: LoanConditions) {
        binding.amountSlider.addOnChangeListener { slider, _, _ ->
            binding.loanAmountTextView.text =
                formatAmountText(requireContext(), slider.value.roundToInt())
            binding.returnAmountTextview.text = formatAmountText(
                requireContext(),
                calculateWithPercentage(slider.value.roundToInt(), conditions.percent).toInt()
            )
        }
    }

    private fun setViewsVisibility(visible: Boolean) {
        binding.amountHeadlineTextView.isVisible = visible
        binding.loanAmountTextView.isVisible = visible
        binding.amountSlider.isVisible = visible
        binding.minValueTextView.isVisible = visible
        binding.maxValueTextView.isVisible = visible
        binding.divider.isVisible = visible
        binding.percentHeadlineTextview.isVisible = visible
        binding.percentTextview.isVisible = visible
        binding.periodTextView.isVisible = visible
        binding.secondDivider.isVisible = visible
        binding.returnAmountHeadlineTextview.isVisible = visible
        binding.returnAmountTextview.isVisible = visible
        binding.continueButton.isVisible = visible
        binding.verticalDivider.isVisible = visible
        binding.periodHeadlineTextview.isVisible = visible


    }
}