package com.example.shapovalov.presentation.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shapovalov.HttpExceptionHandler
import com.example.shapovalov.InputErrorType
import com.example.shapovalov.IoExceptionHandler
import com.example.shapovalov.LiveEvent
import com.example.shapovalov.MutableLiveEvent
import com.example.shapovalov.ErrorType
import com.example.shapovalov.SingleLiveEvent
import com.example.shapovalov.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val router: RegistrationRouter,
    private val ioExceptionHandler: IoExceptionHandler,
    private val httpExceptionHandler: HttpExceptionHandler
) : ViewModel() {

    private val _state: MutableLiveData<RegistrationState> = MutableLiveData(RegistrationState.Initial)
    val state: LiveData<RegistrationState> = _state

    private val _showResponseError = SingleLiveEvent<ErrorType>()
    val showResponseError: LiveData<ErrorType> = _showResponseError

    private val _showInputError = SingleLiveEvent<InputErrorType>()
    val showInputError: LiveData<InputErrorType> = _showInputError

    private val _displayRegistrationSuccess = MutableLiveEvent()
    val displayRegistrationSuccess: LiveEvent = _displayRegistrationSuccess

    fun register(name: String, password: String) {

        if (name.isBlank()) {
            handleInputError(InputErrorType.NAME_EMPTY)
            return

        } else if (password.isBlank()) {
            handleInputError(InputErrorType.PASSWORD_EMPTY)
            return
        }

        viewModelScope.launch {
            _state.value = RegistrationState.Loading

            try {
                registerUseCase.invoke(name, password)
                _displayRegistrationSuccess()
                router.openLogin()

            } catch (e: IOException) {
                _state.value = RegistrationState.Error(ioExceptionHandler.handleException(e))

            } catch (e: HttpException) {
                _state.value = RegistrationState.Initial
                handleNetworkError(httpExceptionHandler.handleException(e))
            }
        }
    }

    private fun handleNetworkError(error: ErrorType) {
        _showResponseError(error)
    }

    private fun handleInputError(error: InputErrorType) {
        _showInputError(error)
    }

    fun restoreInitialState() {
        _state.value = RegistrationState.Initial
    }

    fun navigateToLogin() = router.openLogin()

}