package com.example.shapovalov.ui

import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getLoanDetailsScreen(loanId: Int) = FragmentScreen{ DetailsFragment.newInstance(loanId) }