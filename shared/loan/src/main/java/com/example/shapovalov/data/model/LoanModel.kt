package com.example.shapovalov.data.model

import com.example.shapovalov.domain.entity.LoanStatus
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class LoanModel(
    val amount: Int,
    val date: String,
    val firstName: String,
    val id: Int,
    val lastName: String,
    val percent: Double,
    val period: Int,
    val phoneNumber: String,

    @SerializedName("state")
    val status: LoanStatus
)