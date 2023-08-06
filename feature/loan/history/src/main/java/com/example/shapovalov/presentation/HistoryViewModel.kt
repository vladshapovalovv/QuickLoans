package com.example.shapovalov.presentation

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shapovalov.HttpExceptionHandler
import com.example.shapovalov.IoExceptionHandler
import com.example.shapovalov.LiveEvent
import com.example.shapovalov.MutableLiveEvent
import com.example.shapovalov.domain.entity.Loan
import com.example.shapovalov.domain.usecase.GetLoansListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getLoansListUseCase: GetLoansListUseCase,
    private val router: HistoryRouter,
    private val ioExceptionHandler: IoExceptionHandler,
    private val httpExceptionHandler: HttpExceptionHandler
) : ViewModel() {

    private val _state: MutableLiveData<HistoryState> = MutableLiveData(HistoryState.Initial)
    val state: LiveData<HistoryState> = _state

    private val _showInstructions = MutableLiveEvent()
    val showInstructions: LiveEvent = _showInstructions

    init {
        loadData()
    }
    fun loadData() {
        viewModelScope.launch {
            _state.value = HistoryState.Loading
            try {
                val loans = getLoansListUseCase.invoke()
                _state.value = if (loans.isEmpty()) {
                    HistoryState.Empty
                } else {
                    HistoryState.Content(loans)
                }
            } catch (e: IOException) {
                _state.value = HistoryState.Error(ioExceptionHandler.handleException(e))

            } catch (e: HttpException) {
                _state.value = HistoryState.Error(httpExceptionHandler.handleException(e))
            }
        }
    }

     fun checkIfShownBefore(sharedPreferences: SharedPreferences){

    }

    fun navigateToDetails(loan: Loan) {
        router.openDetails(loan.id)
    }

    fun navigateToCreateLoan(){
        router.openCreateLoan()
    }

    fun navigateToProfile(){
        router.openProfile()
    }
}