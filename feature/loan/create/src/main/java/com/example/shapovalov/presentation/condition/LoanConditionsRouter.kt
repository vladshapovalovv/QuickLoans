package com.example.shapovalov.presentation.condition

import com.example.shapovalov.domain.entity.LoanConditions

interface LoanConditionsRouter {

    fun close()

    fun toPersonalInfo(loanConditions: LoanConditions, amount: Int)

}