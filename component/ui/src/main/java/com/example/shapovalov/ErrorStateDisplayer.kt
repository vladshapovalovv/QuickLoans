package com.example.shapovalov

import android.content.Context
import com.example.shapovalov.component.ui.R

enum class ErrorType {
    CONNECTION_FAILED,
    TIMEOUT,
    SERVER_NOT_FOUND,
    SSL_HANDSHAKE_FAILED,
    INVALID_CREDENTIALS,
    ACCESS_DENIED,
    NOT_EXIST,
    UNKNOWN_ERROR,
    ALREADY_EXISTS;

    fun getErrorMessage(context: Context): String {
        return when (this) {
            CONNECTION_FAILED -> context.getString(R.string.connection_failed_message)
            TIMEOUT -> context.getString(R.string.timeout_message)
            SERVER_NOT_FOUND -> context.getString(R.string.server_not_found_message)
            SSL_HANDSHAKE_FAILED -> context.getString(R.string.ssl_handshake_failed_message)
            UNKNOWN_ERROR -> context.getString(R.string.unknown_error_message)
            INVALID_CREDENTIALS -> context.getString(R.string.invalid_credentials_error_message)
            ACCESS_DENIED -> context.getString(R.string.access_denied_error_message)
            NOT_EXIST -> context.getString(R.string.user_not_exist_error_message)
            ALREADY_EXISTS -> context.getString(R.string.user_already_exist_error_message)
        }
    }
}

enum class InputErrorType{
    NAME_EMPTY,
    PASSWORD_EMPTY,
    FIELD_EMPTY;

    fun getErrorMessage(context: Context): String {
        return when (this) {
            NAME_EMPTY -> context.getString(R.string.name_field_empty)
            PASSWORD_EMPTY -> context.getString(R.string.password_field_empty)
            FIELD_EMPTY -> context.getString(R.string.field_empty)
        }
    }
}