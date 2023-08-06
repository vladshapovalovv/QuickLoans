package com.example.shapovalov.navigation.loan

import com.example.shapovalov.presentation.confirmation.ConfirmationRouter
import com.example.shapovalov.ui.getHistoryScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class ConfirmationRouterImpl @Inject constructor(
    private val router: Router
) : ConfirmationRouter {

    override fun toHistory() {
        router.backTo(getHistoryScreen())
    }
}