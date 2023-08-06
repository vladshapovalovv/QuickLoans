package com.example.shapovalov.ui

import android.app.LocaleManager
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.shapovalov.presentation.ProfileViewModel
import com.example.shapovalov.profile.R
import com.example.shapovalov.profile.databinding.FragmentProfileBinding
import com.example.shapovalov.showInstructionsDialog
import dagger.hilt.android.AndroidEntryPoint

private const val ENGLISH_LOCALE = "en"
private const val RUSSIAN_LOCALE = "ru"

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val viewModel: ProfileViewModel by viewModels()

    private val binding: FragmentProfileBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initLanguageSwitcher()
    }

    private fun initListeners() {
        binding.topAppBar.setNavigationOnClickListener { viewModel.exitScreen() }
        binding.instructionButton.setOnClickListener { showInstructionsDialog(requireContext()) }
    }

    private fun initLanguageSwitcher() {
        val currentLanguage = checkCurrentLanguage()
        binding.languageSwitch.isChecked = currentLanguage == RUSSIAN_LOCALE
        binding.languageSwitch.setOnCheckedChangeListener { compoundButton, b ->
            if (currentLanguage == RUSSIAN_LOCALE) {
                switchToDifferentLanguage(ENGLISH_LOCALE)
            } else {
                switchToDifferentLanguage(RUSSIAN_LOCALE)
            }
        }
    }

    private fun switchToDifferentLanguage(language: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireActivity().getSystemService(LocaleManager::class.java)
                .applicationLocales = LocaleList.forLanguageTags(language)
        } else {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(language))
        }
    }

    private fun checkCurrentLanguage(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireActivity().getSystemService(LocaleManager::class.java).applicationLocales.toLanguageTags()
        } else {
            AppCompatDelegate.getApplicationLocales().toLanguageTags()
        }
    }
}