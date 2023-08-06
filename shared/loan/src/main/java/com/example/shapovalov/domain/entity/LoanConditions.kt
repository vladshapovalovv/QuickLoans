package com.example.shapovalov.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoanConditions(
    val maxAmount: Int,
    val percent: Double,
    val period: Int
) : Parcelable
