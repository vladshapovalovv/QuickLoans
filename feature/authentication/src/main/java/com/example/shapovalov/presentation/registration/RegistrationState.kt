package com.example.shapovalov.presentation.registration

import com.example.shapovalov.ErrorType

sealed interface RegistrationState {

    object Initial : RegistrationState

    object Loading : RegistrationState

    data class Error(val error: ErrorType) : RegistrationState
}