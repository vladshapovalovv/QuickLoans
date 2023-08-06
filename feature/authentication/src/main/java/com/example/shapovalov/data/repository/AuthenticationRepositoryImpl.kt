package com.example.shapovalov.data.repository

import android.content.SharedPreferences
import com.example.shapovalov.data.api.AuthenticationApi
import com.example.shapovalov.data.model.AuthenticationRequestModel
import com.example.shapovalov.domain.repository.AuthenticationRepository
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val authenticationApi: AuthenticationApi,
    private val sharedPreferences: SharedPreferences
) : AuthenticationRepository {

    override suspend fun login(name: String, password: String) {
        authenticationApi.login(AuthenticationRequestModel(name, password)).also { requestBody ->
            saveApiToken(requestBody.string())
        }
    }

    override suspend fun register(name: String, password: String) {
        authenticationApi.register(AuthenticationRequestModel(name, password))
    }

    private fun saveApiToken(apiToken: String) {
        sharedPreferences.edit().putString("API_KEY", apiToken).apply()
    }

}