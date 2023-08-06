package com.example.shapovalov.navigation.authentification

import com.example.shapovalov.presentation.registration.RegistrationRouter
import com.example.shapovalov.ui.getLoginScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class RegistrationRouterImpl @Inject constructor(private val router:Router): RegistrationRouter {
    override fun openLogin() {
        router.backTo(getLoginScreen())
    }

}