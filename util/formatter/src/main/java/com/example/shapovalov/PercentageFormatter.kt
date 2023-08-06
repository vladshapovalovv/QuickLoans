package com.example.shapovalov

import java.text.DecimalFormat

fun formatAsPercentage(value: Double): String {
    val decimalFormat = DecimalFormat("#,##0.00")
    decimalFormat.positiveSuffix = " %"
    return decimalFormat.format(value)
}