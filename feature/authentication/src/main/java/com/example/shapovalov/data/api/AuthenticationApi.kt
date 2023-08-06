package com.example.shapovalov.data.api

import com.example.shapovalov.data.model.AuthenticationRequestModel
import com.example.shapovalov.data.model.RegistrationResponseModel
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationApi {

    @POST("/login")
    suspend fun login(@Body loginRequest: AuthenticationRequestModel): ResponseBody

    @POST("/registration")
    suspend fun register(@Body registrationRequest: AuthenticationRequestModel): RegistrationResponseModel
}