package com.example.shapovalov.di

import com.example.shapovalov.data.api.LoansApi
import com.example.shapovalov.data.repository.LoansRepositoryImpl
import com.example.shapovalov.domain.repository.LoansRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LoanModule {

    companion object {
        @Provides
        @Singleton
        fun provideLoansApi(retrofit: Retrofit): LoansApi {
            return retrofit.create( LoansApi::class.java)
        }
    }

    @Singleton
    @Binds
    fun bindLoansRepository(impl: LoansRepositoryImpl): LoansRepository
}