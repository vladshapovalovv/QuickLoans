package com.example.shapovalov.domain.usecase

import com.example.shapovalov.domain.repository.AuthenticationRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) {
    suspend operator fun invoke(name: String, password: String){
       repository.register(name, password)
    }
}