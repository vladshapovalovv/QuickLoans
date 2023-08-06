package com.example.shapovalov.navigation.profile

import com.example.shapovalov.presentation.ProfileRouter
import com.example.shapovalov.ui.getHistoryScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class ProfileRouterImpl @Inject constructor(
    private val router: Router
) : ProfileRouter {

    override fun closeProfile() {
        router.exit()
    }
}