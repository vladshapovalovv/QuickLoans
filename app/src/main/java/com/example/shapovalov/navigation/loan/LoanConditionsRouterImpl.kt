package com.example.shapovalov.navigation.loan

import com.example.shapovalov.domain.entity.LoanConditions
import com.example.shapovalov.presentation.condition.LoanConditionsRouter
import com.example.shapovalov.ui.getPersonalInfoScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class LoanConditionsRouterImpl @Inject constructor(
    private val router: Router
): LoanConditionsRouter {

    override fun close() {
        router.exit()
    }

    override fun toPersonalInfo(loanConditions: LoanConditions, amount: Int) {
        router.navigateTo(getPersonalInfoScreen(loanConditions, amount))
    }
}