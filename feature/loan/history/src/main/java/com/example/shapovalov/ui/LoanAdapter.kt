package com.example.shapovalov.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shapovalov.domain.entity.Loan
import com.example.shapovalov.feature.loan.history.databinding.LoanItemBinding

class LoanAdapter(
    private val loanSelected: (Loan) -> Unit
) : RecyclerView.Adapter<LoanViewHolder>() {

    var items: List<Loan> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoanViewHolder {
        val itemBinding =
            LoanItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoanViewHolder(itemBinding, loanSelected)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: LoanViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun insertItem(loan: Loan) {
        items = items + loan
        notifyItemInserted(items.lastIndex)
    }
}