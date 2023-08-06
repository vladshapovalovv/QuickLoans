package com.example.shapovalov

import android.content.Context
import com.example.shapovalov.util.formatter.R
import java.text.NumberFormat

fun formatAmountText(context: Context, amount: Int): String {
	val numberFormatter = NumberFormat.getNumberInstance(RUSSIAN_LOCALE)
	val formattedAmount = numberFormatter.format(amount)
	return buildString {
        append(formattedAmount)
        append(context.getString(R.string.amount_postfix_ruble))
    }
}