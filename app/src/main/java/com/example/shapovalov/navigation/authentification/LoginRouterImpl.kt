package com.example.shapovalov.navigation.authentification

import com.example.shapovalov.presentation.login.LoginRouter
import com.example.shapovalov.ui.getHistoryScreen
import com.example.shapovalov.ui.getRegistrationScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class LoginRouterImpl @Inject constructor(private val router: Router): LoginRouter {

    override fun openRegistration() {
        router.navigateTo(getRegistrationScreen())
    }

    override fun openHistory() {
       router.newRootScreen(getHistoryScreen())
    }

}