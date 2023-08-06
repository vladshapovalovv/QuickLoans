package com.example.shapovalov.domain.repository

import com.example.shapovalov.domain.entity.Loan
import com.example.shapovalov.domain.entity.LoanConditions
import com.example.shapovalov.domain.entity.LoanCreation

interface LoansRepository {

    suspend fun getAll(): List<Loan>

    suspend fun getDetails(id: Int): Loan

    suspend fun getConditions(): LoanConditions

    suspend fun create(loanCreation: LoanCreation): Loan

}