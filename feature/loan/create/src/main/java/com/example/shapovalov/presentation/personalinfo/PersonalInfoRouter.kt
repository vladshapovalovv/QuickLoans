package com.example.shapovalov.presentation.personalinfo

import com.example.shapovalov.domain.entity.Loan

interface PersonalInfoRouter {

    fun close()

    fun toConfirmation(loan: Loan)

}