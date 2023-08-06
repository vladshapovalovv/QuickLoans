package com.example.shapovalov.ui

import com.example.shapovalov.domain.entity.Loan
import com.example.shapovalov.domain.entity.LoanConditions
import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getCreateLoanScreen() = FragmentScreen { LoanConditionsFragment() }

fun getConfirmationScreen(loan: Loan) = FragmentScreen { ConfirmationFragment.newInstance(loan)}

fun getPersonalInfoScreen(conditions: LoanConditions, amount: Int) =
    FragmentScreen { PersonalInfoFragment.newInstance(conditions, amount) }