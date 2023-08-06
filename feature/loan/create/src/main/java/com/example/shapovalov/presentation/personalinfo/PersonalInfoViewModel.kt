package com.example.shapovalov.presentation.personalinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shapovalov.HttpExceptionHandler
import com.example.shapovalov.InputErrorType
import com.example.shapovalov.IoExceptionHandler
import com.example.shapovalov.SingleLiveEvent
import com.example.shapovalov.domain.entity.Loan
import com.example.shapovalov.domain.entity.LoanCreation
import com.example.shapovalov.domain.usecase.CreateLoanUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class PersonalInfoViewModel @Inject constructor(
    private val router: PersonalInfoRouter,
    private val ioExceptionHandler: IoExceptionHandler,
    private val httpExceptionHandler: HttpExceptionHandler,
    private val createLoanUseCase: CreateLoanUseCase
) : ViewModel() {

    private val _state: MutableLiveData<PersonalInfoState> = MutableLiveData(PersonalInfoState.Initial)
    val state: LiveData<PersonalInfoState> = _state

    private val _showFirstNameError = SingleLiveEvent<InputErrorType>()
    val showFirstNameError: LiveData<InputErrorType> = _showFirstNameError

    private val _showLastNameError = SingleLiveEvent<InputErrorType>()
    val showLastNameError: LiveData<InputErrorType> = _showLastNameError

    private val _showPhoneError = SingleLiveEvent<InputErrorType>()
    val showPhoneError : LiveData<InputErrorType> = _showPhoneError

    fun createLoan(loan: LoanCreation) {
        if (loan.firstName.isBlank()) {
            InputErrorType.FIELD_EMPTY.handleFirstNameError()
            return
        }
        if(loan.lastName.isBlank()){
            InputErrorType.FIELD_EMPTY.handleLastNameError()
            return
        }
        if(loan.phoneNumber.isBlank()){
            InputErrorType.FIELD_EMPTY.handlePhoneNumberError()
            return
        }

        viewModelScope.launch {
            _state.value = PersonalInfoState.Loading
            try {
                val loanResponse = createLoanUseCase.invoke(loan)
                navigateToConfirmation(loanResponse)
            } catch (e: IOException) {
                _state.value = PersonalInfoState.Error(ioExceptionHandler.handleException(e))
            } catch (e: HttpException) {
                _state.value = PersonalInfoState.Error(httpExceptionHandler.handleException(e))
            }
        }
    }

    fun exitScreen() {
        router.close()
    }

    private fun InputErrorType.handleFirstNameError() {
        this@PersonalInfoViewModel._showFirstNameError(this)
    }

    private fun InputErrorType.handleLastNameError() {
        this@PersonalInfoViewModel._showLastNameError(this)
    }

    private fun InputErrorType.handlePhoneNumberError() {
        this@PersonalInfoViewModel._showPhoneError(this)
    }

    private fun navigateToConfirmation(loan: Loan) {
        router.toConfirmation(loan)
    }
}