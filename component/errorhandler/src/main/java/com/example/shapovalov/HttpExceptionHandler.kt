package com.example.shapovalov

import retrofit2.HttpException
import javax.inject.Inject

class HttpExceptionHandler @Inject constructor(){
    fun handleException(exception: HttpException): ErrorType {
        return when (exception.code()) {
            400 -> ErrorType.ALREADY_EXISTS
            401 -> ErrorType.INVALID_CREDENTIALS
            403 -> ErrorType.ACCESS_DENIED
            404 -> ErrorType.NOT_EXIST
            else -> ErrorType.UNKNOWN_ERROR
        }
    }
}