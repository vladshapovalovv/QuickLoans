package com.example.shapovalov.presentation.condition

import com.example.shapovalov.ErrorType
import com.example.shapovalov.domain.entity.LoanConditions

interface LoanConditionsState {

    object Initial : LoanConditionsState

    object Loading : LoanConditionsState

    data class Content(val conditions: LoanConditions): LoanConditionsState

    data class Error(val error: ErrorType) : LoanConditionsState

}