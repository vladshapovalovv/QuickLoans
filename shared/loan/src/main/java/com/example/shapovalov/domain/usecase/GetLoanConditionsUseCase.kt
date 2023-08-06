package com.example.shapovalov.domain.usecase

import com.example.shapovalov.domain.repository.LoansRepository
import javax.inject.Inject

class GetLoanConditionsUseCase @Inject constructor(
    private val repository: LoansRepository
) {
    suspend operator fun invoke() = repository.getConditions()
}