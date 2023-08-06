package com.example.shapovalov.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shapovalov.HttpExceptionHandler
import com.example.shapovalov.IoExceptionHandler
import com.example.shapovalov.domain.usecase.GetLoanDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getLoanDetailsUseCase: GetLoanDetailsUseCase,
    private val router: DetailsRouter,
    private val ioExceptionHandler: IoExceptionHandler,
    private val httpExceptionHandler: HttpExceptionHandler
): ViewModel() {

    private val _state: MutableLiveData<DetailsState> = MutableLiveData(DetailsState.Initial)
    val state: LiveData<DetailsState> = _state

    fun getDetails(loanId: Int) {
        viewModelScope.launch {
            _state.value = DetailsState.Loading
            try {
                _state.value = DetailsState.Content(getLoanDetailsUseCase.invoke(loanId))
            } catch (e: IOException) {
                _state.value = DetailsState.Error(ioExceptionHandler.handleException(e))
            } catch (e: HttpException) {
                _state.value = DetailsState.Error(httpExceptionHandler.handleException(e))
            }
        }
    }

    fun closeDetails(){
        router.close()
    }
}