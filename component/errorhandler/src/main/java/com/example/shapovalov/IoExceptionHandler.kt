package com.example.shapovalov

import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.net.ssl.SSLHandshakeException

class IoExceptionHandler @Inject constructor() {
    fun handleException(exception: IOException): ErrorType {
        return when (exception) {
            is ConnectException -> ErrorType.CONNECTION_FAILED
            is SocketTimeoutException -> ErrorType.TIMEOUT
            is UnknownHostException -> ErrorType.SERVER_NOT_FOUND
            is SSLHandshakeException -> ErrorType.SSL_HANDSHAKE_FAILED
            else -> {
                ErrorType.UNKNOWN_ERROR
            }
        }
    }
}