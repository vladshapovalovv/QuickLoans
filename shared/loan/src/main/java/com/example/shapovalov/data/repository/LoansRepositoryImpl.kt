package com.example.shapovalov.data.repository

import com.example.shapovalov.data.api.LoansApi
import com.example.shapovalov.data.converter.LoanConditionsConverter
import com.example.shapovalov.data.converter.LoanConverter
import com.example.shapovalov.data.converter.LoanCreationConverter
import com.example.shapovalov.domain.entity.Loan
import com.example.shapovalov.domain.entity.LoanConditions
import com.example.shapovalov.domain.entity.LoanCreation
import com.example.shapovalov.domain.repository.LoansRepository
import javax.inject.Inject

class LoansRepositoryImpl @Inject constructor(
    private val loansApi: LoansApi,
    private val loanConverter: LoanConverter,
    private val loanCreationConverter: LoanCreationConverter,
    private val loanConditionsConverter: LoanConditionsConverter
) : LoansRepository {
    override suspend fun getAll(): List<Loan> {
        return loansApi.getAll().map { loanConverter.convert(it) }
    }

    override suspend fun getDetails(id: Int): Loan {
        return loanConverter.convert(loansApi.getDetails(id))
    }

    override suspend fun getConditions(): LoanConditions {
        return loanConditionsConverter.convert(loansApi.getConditions())
    }

    override suspend fun create(loanCreation: LoanCreation): Loan {
        loansApi.create(loanCreationConverter.convert(loanCreation)).also {
            return loanConverter.convert(it)
        }
    }
}