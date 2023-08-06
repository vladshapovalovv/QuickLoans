package com.example.shapovalov.data.converter

import com.example.shapovalov.data.model.LoanConditionsModel
import com.example.shapovalov.domain.entity.LoanConditions
import javax.inject.Inject

class LoanConditionsConverter @Inject constructor() {

    fun convert(from: LoanConditionsModel): LoanConditions =
        LoanConditions(
            maxAmount = from.maxAmount,
            percent = from.percent,
            period = from.period
        )
}