package com.example.shapovalov.network

import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenInterceptor @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        if (request.url.encodedPath in listOf("/login", "/registration")) {
            return chain.proceed(request)
        }
        val token = sharedPreferences.getString("API_KEY", "").orEmpty()
        val modifiedRequest = request.newBuilder()
            .addHeader("Authorization", token)
            .build()

        return chain.proceed(modifiedRequest)
    }
}