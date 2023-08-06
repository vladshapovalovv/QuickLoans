package com.example.shapovalov.navigation.loan

import com.example.shapovalov.domain.entity.Loan
import com.example.shapovalov.presentation.personalinfo.PersonalInfoRouter
import com.example.shapovalov.ui.getConfirmationScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class PersonalInfoRouterImpl @Inject constructor(
    private val router: Router
): PersonalInfoRouter {

    override fun close() {
        router.exit()
    }

    override fun toConfirmation(loan: Loan) {
        router.replaceScreen(getConfirmationScreen(loan))
    }
}