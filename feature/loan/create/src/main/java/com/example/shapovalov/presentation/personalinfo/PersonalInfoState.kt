package com.example.shapovalov.presentation.personalinfo

import com.example.shapovalov.ErrorType
import com.example.shapovalov.domain.entity.Loan

interface PersonalInfoState {

    object Initial : PersonalInfoState

    object Loading : PersonalInfoState

    data class Error(val error: ErrorType) : PersonalInfoState

}