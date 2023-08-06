package com.example.shapovalov.presentation

import com.example.shapovalov.ErrorType
import com.example.shapovalov.domain.entity.Loan

interface DetailsState {

    object Initial : DetailsState

    object Loading : DetailsState

    data class Content(val loan: Loan) : DetailsState

    data class Error(val error: ErrorType) : DetailsState

}