package com.example.shapovalov.domain.usecase

import com.example.shapovalov.domain.repository.LoansRepository
import javax.inject.Inject

class GetLoanDetailsUseCase @Inject constructor(
    private val repository: LoansRepository
) {
    suspend operator fun invoke(id: Int) = repository.getDetails(id)
}