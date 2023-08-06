package com.example.shapovalov.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.shapovalov.domain.entity.Loan
import com.example.shapovalov.feature.loan.history.R
import com.example.shapovalov.feature.loan.history.databinding.LoanItemBinding
import com.example.shapovalov.formatAmountText
import com.example.shapovalov.formatDate
import com.example.shapovalov.getStatusIndicatorColor

class LoanViewHolder(
    private val binding: LoanItemBinding,
    private val loanSelected: (Loan) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(loan: Loan) {
        binding.amountTextView.text = formatAmountText(itemView.context, loan.amount)
        binding.dateTextView.text = buildString {
            append(itemView.context.getString(com.example.shapovalov.component.ui.R.string.loan_date))
            append(formatDate(loan.date))
        }
        binding.statusTextView.text = formatLoanStatus(itemView.context, loan.status)
        binding.statusImageView.drawable.setTint(getStatusIndicatorColor(itemView.context, loan.status))
        itemView.setOnClickListener { loanSelected(loan) }

    }

}