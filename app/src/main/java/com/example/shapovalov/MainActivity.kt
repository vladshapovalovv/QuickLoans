package com.example.shapovalov

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.shapovalov.navigation.LoansNavigator
import com.example.shapovalov.navigation.checkIfUserSinged
import com.example.shapovalov.ui.getCreateLoanScreen
import com.example.shapovalov.ui.getHistoryScreen
import com.example.shapovalov.ui.getLoanDetailsScreen
import com.example.shapovalov.ui.getLoginScreen
import com.example.shapovalov.ui.getProfileScreen
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

	@Inject
	lateinit var router: Router

	@Inject
	lateinit var navigatorHolder: NavigatorHolder

	@Inject
	lateinit var sharedPreferences: SharedPreferences

	private val navigator = LoansNavigator(this, R.id.container)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		navigatorHolder.setNavigator(navigator)
		handleStartNavigation()
	}

	private fun handleStartNavigation() {
		if (checkIfUserSinged(sharedPreferences)){
			router.newRootScreen(getHistoryScreen())
		}else{
			router.navigateTo(getLoginScreen())
		}
	}
	override fun onResume() {
		super.onResume()
		navigatorHolder.setNavigator(navigator)
	}

	override fun onPause() {
		super.onPause()
		navigatorHolder.removeNavigator()
	}
}