package com.example.shapovalov.di

import com.example.shapovalov.data.api.AuthenticationApi
import com.example.shapovalov.data.repository.AuthenticationRepositoryImpl
import com.example.shapovalov.domain.repository.AuthenticationRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AuthenticationModule {
    companion object {
        @Provides
        @Singleton
        fun provideAuthenticationApi(retrofit: Retrofit): AuthenticationApi {
            return retrofit.create(AuthenticationApi::class.java)
        }
    }
    @Singleton
    @Binds
    fun bindAuthenticationRepository(impl: AuthenticationRepositoryImpl): AuthenticationRepository
}