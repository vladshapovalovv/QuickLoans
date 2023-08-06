package com.example.shapovalov.navigation.loan

import com.example.shapovalov.presentation.HistoryRouter
import com.example.shapovalov.ui.getCreateLoanScreen
import com.example.shapovalov.ui.getLoanDetailsScreen
import com.example.shapovalov.ui.getProfileScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class HistoryRouterImpl @Inject constructor(
    private val router: Router
) : HistoryRouter {

    override fun openDetails(loanId: Int) {
        router.navigateTo(getLoanDetailsScreen(loanId))
    }

    override fun openCreateLoan() {
        router.navigateTo(getCreateLoanScreen())
    }

    override fun openProfile() {
        router.navigateTo(getProfileScreen())
    }
}