package com.example.shapovalov.ui

import android.content.Context
import com.example.shapovalov.domain.entity.LoanStatus
import com.example.shapovalov.shared.loan.R

fun formatLoanStatus(context: Context, status: LoanStatus) =
	when (status) {
		LoanStatus.APPROVED   -> context.getString(R.string.approved)
		LoanStatus.REGISTERED -> context.getString(R.string.registered)
		LoanStatus.REJECTED   -> context.getString(R.string.rejected)
	}