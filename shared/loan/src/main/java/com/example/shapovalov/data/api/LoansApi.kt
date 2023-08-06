package com.example.shapovalov.data.api

import com.example.shapovalov.data.model.LoanConditionsModel
import com.example.shapovalov.data.model.LoanCreationModel
import com.example.shapovalov.data.model.LoanModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LoansApi {
    @GET("/loans/all")
    suspend fun getAll(): List<LoanModel>

    @GET("/loans/{id}")
    suspend fun getDetails(@Path("id") loanId: Int): LoanModel

    @GET("/loans/conditions")
    suspend fun getConditions(): LoanConditionsModel

    @POST("/loans")
    suspend fun create(@Body createLoanModel: LoanCreationModel) : LoanModel

}