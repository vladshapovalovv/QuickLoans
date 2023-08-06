package com.example.shapovalov.presentation.login

import com.example.shapovalov.ErrorType

sealed interface LoginState {

    object Initial : LoginState

    object Loading : LoginState

    data class Error(val error: ErrorType) : LoginState
}