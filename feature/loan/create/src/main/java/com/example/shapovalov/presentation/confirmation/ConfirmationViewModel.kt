package com.example.shapovalov.presentation.confirmation

import android.os.Bundle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConfirmationViewModel @Inject constructor(
    private val router: ConfirmationRouter
) : ViewModel() {

    fun navigateToHistory() {
        router.toHistory()
    }
}