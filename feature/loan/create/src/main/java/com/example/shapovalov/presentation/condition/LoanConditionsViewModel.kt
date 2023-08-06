package com.example.shapovalov.presentation.condition

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shapovalov.HttpExceptionHandler
import com.example.shapovalov.IoExceptionHandler
import com.example.shapovalov.domain.entity.LoanConditions
import com.example.shapovalov.domain.usecase.GetLoanConditionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class LoanConditionsViewModel @Inject constructor(
    private val router: LoanConditionsRouter,
    private val getLoanConditionsUseCase: GetLoanConditionsUseCase,
    private val ioExceptionHandler: IoExceptionHandler,
    private val httpExceptionHandler: HttpExceptionHandler,
) : ViewModel() {

    private val _state: MutableLiveData<LoanConditionsState> =
        MutableLiveData(LoanConditionsState.Initial)
    val state: LiveData<LoanConditionsState> = _state

    init {
        getLoanConditions()
    }

   fun getLoanConditions() {
       viewModelScope.launch {
           _state.value = LoanConditionsState.Loading
           try {
               val conditions = getLoanConditionsUseCase.invoke()
               _state.value = LoanConditionsState.Content(conditions)
           } catch (e: IOException) {
               _state.value = LoanConditionsState.Error(ioExceptionHandler.handleException(e))
           } catch (e: HttpException) {
               _state.value = LoanConditionsState.Error(httpExceptionHandler.handleException(e))
           }
        }
    }

    fun exitScreen() {
        router.close()
    }

    fun navigateToPersonalInfo(loanConditions: LoanConditions, amount: Int) {
        router.toPersonalInfo(loanConditions, amount)
    }
}