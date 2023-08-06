package com.example.shapovalov.presentation

import com.example.shapovalov.ErrorType
import com.example.shapovalov.domain.entity.Loan

interface HistoryState {

    object Initial : HistoryState

    object Loading : HistoryState

    data class Content(val loans: List<Loan>) : HistoryState
    object Empty : HistoryState

    data class Error(val error: ErrorType) : HistoryState

}