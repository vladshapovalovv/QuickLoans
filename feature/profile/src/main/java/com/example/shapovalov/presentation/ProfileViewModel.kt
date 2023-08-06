package com.example.shapovalov.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
   private val router: ProfileRouter
) : ViewModel() {

  fun exitScreen() {
     router.closeProfile()
  }

}