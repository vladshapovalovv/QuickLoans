package com.example.shapovalov.di

import com.example.shapovalov.navigation.authentification.LoginRouterImpl
import com.example.shapovalov.navigation.authentification.RegistrationRouterImpl
import com.example.shapovalov.navigation.loan.ConfirmationRouterImpl
import com.example.shapovalov.navigation.loan.DetailsRouterImpl
import com.example.shapovalov.navigation.loan.HistoryRouterImpl
import com.example.shapovalov.navigation.loan.LoanConditionsRouterImpl
import com.example.shapovalov.navigation.loan.PersonalInfoRouterImpl
import com.example.shapovalov.navigation.profile.ProfileRouterImpl
import com.example.shapovalov.presentation.DetailsRouter
import com.example.shapovalov.presentation.HistoryRouter
import com.example.shapovalov.presentation.ProfileRouter
import com.example.shapovalov.presentation.condition.LoanConditionsRouter
import com.example.shapovalov.presentation.confirmation.ConfirmationRouter
import com.example.shapovalov.presentation.login.LoginRouter
import com.example.shapovalov.presentation.personalinfo.PersonalInfoRouter
import com.example.shapovalov.presentation.registration.RegistrationRouter
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {
    companion object {
        @Provides
        @Singleton
        fun provideCicerone(): Cicerone<Router> = Cicerone.create()

        @Provides
        @Singleton
        fun provideRouter(cicerone: Cicerone<Router>): Router = cicerone.router

        @Provides
        @Singleton
        fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder =
            cicerone.getNavigatorHolder()
    }

    @Singleton
    @Binds
    fun bindLoginRouter(impl: LoginRouterImpl): LoginRouter

    @Singleton
    @Binds
    fun bindRegistrationRouter(impl: RegistrationRouterImpl): RegistrationRouter

    @Singleton
    @Binds
    fun bindHistoryRouter(impl: HistoryRouterImpl): HistoryRouter

    @Singleton
    @Binds
    fun bindCreateLoanRouter(impl: LoanConditionsRouterImpl): LoanConditionsRouter

    @Singleton
    @Binds
    fun bindDetailsRouter(impl: DetailsRouterImpl): DetailsRouter

    @Singleton
    @Binds
    fun bindPersonalInfoRouter(impl: PersonalInfoRouterImpl): PersonalInfoRouter

    @Singleton
    @Binds
    fun bindConfirmationRouter(impl: ConfirmationRouterImpl): ConfirmationRouter

    @Singleton
    @Binds
    fun bindProfileRouter(impl: ProfileRouterImpl): ProfileRouter


}