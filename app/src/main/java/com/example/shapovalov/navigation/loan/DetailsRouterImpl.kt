package com.example.shapovalov.navigation.loan

import com.example.shapovalov.presentation.DetailsRouter
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class DetailsRouterImpl @Inject constructor(
    private val router: Router
) : DetailsRouter {

    override fun close() {
        router.exit()
    }
}