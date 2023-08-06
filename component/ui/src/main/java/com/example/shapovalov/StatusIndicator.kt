package com.example.shapovalov

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat
import com.example.shapovalov.component.ui.R
import com.example.shapovalov.domain.entity.LoanStatus

fun getStatusIndicatorColor(context: Context, status: LoanStatus): Int {
    return when (status) {
        LoanStatus.APPROVED -> ContextCompat.getColor(context, R.color.indicator_approved)
        LoanStatus.REGISTERED -> ContextCompat.getColor(context, R.color.indicator_registered)
        LoanStatus.REJECTED -> ContextCompat.getColor(context, R.color.indicator_rejected)
    }
}