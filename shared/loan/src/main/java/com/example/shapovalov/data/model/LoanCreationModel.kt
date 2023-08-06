package com.example.shapovalov.data.model

data class LoanCreationModel(
    val amount: Int,
    val firstName: String,
    val lastName: String,
    val percent: Double,
    val period: Int,
    val phoneNumber: String
)