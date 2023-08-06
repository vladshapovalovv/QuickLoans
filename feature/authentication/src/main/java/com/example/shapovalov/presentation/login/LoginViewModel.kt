package com.example.shapovalov.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shapovalov.ErrorType
import com.example.shapovalov.HttpExceptionHandler
import com.example.shapovalov.InputErrorType
import com.example.shapovalov.IoExceptionHandler
import com.example.shapovalov.SingleLiveEvent
import com.example.shapovalov.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val router: LoginRouter,
    private val ioExceptionHandler: IoExceptionHandler,
    private val httpExceptionHandler: HttpExceptionHandler
) : ViewModel() {

    private val _state: MutableLiveData<LoginState> = MutableLiveData(LoginState.Initial)
    val state: LiveData<LoginState> = _state

    private val _showResponseError = SingleLiveEvent<ErrorType>()
    val showResponseError: LiveData<ErrorType> = _showResponseError

    private val _showInputError = SingleLiveEvent<InputErrorType>()
    val showInputError: LiveData<InputErrorType> = _showInputError

    fun login(name: String, password: String) {

        if (name.isBlank()) {
            handleInputError(InputErrorType.NAME_EMPTY)
            return
        } else if (password.isBlank()) {
            handleInputError(InputErrorType.PASSWORD_EMPTY)
            return
        }
        viewModelScope.launch {
            _state.value = LoginState.Loading

            try {
                loginUseCase.invoke(name, password)
                router.openHistory()
            } catch (e: IOException) {
               _state.value = LoginState.Error(ioExceptionHandler.handleException(e))
            } catch (e: HttpException) {
                _state.value = LoginState.Initial
                handleResponseError(httpExceptionHandler.handleException(e))
            }
        }
    }

    private fun handleResponseError(error: ErrorType) {
        _showResponseError(error)
    }

    private fun handleInputError(error: InputErrorType) {
        _showInputError(error)
    }

    fun restoreInitialState() {
        _state.value = LoginState.Initial
    }

    fun navigateToRegistration() = router.openRegistration()
}