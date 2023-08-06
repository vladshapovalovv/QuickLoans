package com.example.shapovalov.data.converter

import com.example.shapovalov.data.model.LoanCreationModel
import com.example.shapovalov.domain.entity.LoanCreation
import javax.inject.Inject

class LoanCreationConverter @Inject constructor() {

    fun convert(from: LoanCreation): LoanCreationModel =
        LoanCreationModel(
            amount = from.amount.toInt(),
            firstName = from.firstName,
            lastName = from.lastName,
            percent = from.percent,
            period = from.period,
            phoneNumber = from.phoneNumber,
        )

    fun revert(from: LoanCreationModel): LoanCreation =
        LoanCreation(
            amount = from.amount,
            firstName = from.firstName,
            lastName = from.lastName,
            percent = from.percent,
            period = from.period,
            phoneNumber = from.phoneNumber,
        )
}