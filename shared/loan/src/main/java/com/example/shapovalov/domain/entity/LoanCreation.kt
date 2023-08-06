package com.example.shapovalov.domain.entity

data class LoanCreation(
    val amount: Int,
    val firstName: String,
    val lastName: String,
    val percent: Double,
    val period: Int,
    val phoneNumber: String
)