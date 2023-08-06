package com.example.shapovalov.presentation

import com.example.shapovalov.ErrorType

interface ProfileState {

    object Initial : ProfileState

    object Loading : ProfileState

    object Content : ProfileState
    object Empty : ProfileState

    data class Error(val error: ErrorType) : ProfileState


}