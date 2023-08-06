package com.example.shapovalov.data.converter

import com.example.shapovalov.data.model.LoanModel
import com.example.shapovalov.domain.entity.Loan
import javax.inject.Inject

class LoanConverter @Inject constructor() {

    fun convert(from: LoanModel): Loan =
        Loan(
            amount = from.amount,
            firstName = from.firstName,
            lastName = from.lastName,
            percent = from.percent,
            period = from.period,
            phoneNumber = from.phoneNumber,
            id = from.id,
            date = from.date,
            status = from.status,
        )
}