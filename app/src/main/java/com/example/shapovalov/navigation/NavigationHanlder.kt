package com.example.shapovalov.navigation

import android.content.SharedPreferences

fun checkIfUserSinged (sharedPreferences: SharedPreferences): Boolean {
    val apiToken: String? = sharedPreferences.getString("API_KEY", null)
    return !apiToken.isNullOrEmpty()
}