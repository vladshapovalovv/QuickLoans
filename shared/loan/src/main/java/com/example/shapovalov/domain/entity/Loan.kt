package com.example.shapovalov.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Loan(
    val id: Int,
    val amount: Int,
    val date: String,
    val firstName: String,
    val lastName: String,
    val percent: Double,
    val period: Int,
    val phoneNumber: String,
    val status: LoanStatus,
) : Parcelable