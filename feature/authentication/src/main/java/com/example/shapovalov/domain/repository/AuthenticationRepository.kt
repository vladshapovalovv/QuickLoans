package com.example.shapovalov.domain.repository

interface AuthenticationRepository {

    suspend fun login(name: String, password: String)

    suspend fun register(name: String, password: String)

}