package com.example.shapovalov.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.shapovalov.domain.entity.Loan
import com.example.shapovalov.feature.loan.create.R
import com.example.shapovalov.feature.loan.create.databinding.FragmentConfirmationBinding
import com.example.shapovalov.formatAmountText
import com.example.shapovalov.formatAsPercentage
import com.example.shapovalov.formatDateTime
import com.example.shapovalov.getParcelableCompat
import com.example.shapovalov.presentation.confirmation.ConfirmationViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val LOAN = "LOAN"
private var Bundle.loan
    get() = getParcelableCompat<Loan>(LOAN)
    set(value) = putParcelable(LOAN, value)


@AndroidEntryPoint
class ConfirmationFragment : Fragment(R.layout.fragment_confirmation) {
    companion object {
        fun newInstance(loan: Loan) = ConfirmationFragment().apply {
            arguments = Bundle().apply {
                this.loan = loan
            }
        }
    }

    private val binding: FragmentConfirmationBinding by viewBinding()

    private val viewModel: ConfirmationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initLoanData()
    }

    private fun initListeners() {
        binding.navigateButton.setOnClickListener { viewModel.navigateToHistory() }
    }

    private fun initLoanData() {
        with(arguments?.loan) {
            binding.periodTextView.text = this?.period.toString()
            binding.statusTextView.text = this?.status.toString()
            binding.amountTextView.text = formatAmountText(requireContext(), this?.amount ?: 0)
            binding.dateTimeTextView.text = formatDateTime(this?.date.orEmpty())
            binding.nameTextView.text = buildFullNameString(this?.firstName.orEmpty(), this?.lastName.orEmpty())
            binding.percentTextView.text = formatAsPercentage(this?.percent ?: 0.0)
            binding.phoneNumberTextView.text = this?.phoneNumber
        }
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
            append(getString(com.example.shapovalov.component.ui.R.string.space))
            append(com.example.shapovalov.component.ui.R.string.days)
        }
    }
}