package com.example.shapovalov.domain.usecase

import com.example.shapovalov.domain.entity.LoanCreation
import com.example.shapovalov.domain.repository.LoansRepository
import javax.inject.Inject

class CreateLoanUseCase @Inject constructor(
    private val repository: LoansRepository
) {
    suspend operator fun invoke(loanCreation: LoanCreation) = repository.create(loanCreation)
}