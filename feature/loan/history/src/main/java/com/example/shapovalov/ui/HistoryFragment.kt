package com.example.shapovalov.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.shapovalov.feature.loan.history.R
import com.example.shapovalov.feature.loan.history.databinding.FragmentHistoryBinding
import com.example.shapovalov.presentation.HistoryState
import com.example.shapovalov.presentation.HistoryViewModel
import com.example.shapovalov.showInstructionsDialog
import com.example.shapovalov.subscribeSafe
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HistoryFragment: Fragment(R.layout.fragment_history) {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private val viewModel: HistoryViewModel by viewModels()

    private val binding: FragmentHistoryBinding by viewBinding()

    private lateinit var loanAdapter: LoanAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initListeners()
        initObservers()
        showDialog()
    }

    private fun initAdapter() {
        loanAdapter = LoanAdapter(viewModel::navigateToDetails)
        binding.recyclerView.adapter = loanAdapter
    }

    private fun initListeners() {
        binding.newLoanFab.setOnClickListener { viewModel.navigateToCreateLoan() }
        binding.profileImageView.setOnClickListener { viewModel.navigateToProfile() }
        binding.refreshLayout.setOnRefreshListener { viewModel.loadData() }

    }

    private fun initObservers() {
        viewModel.state.observe(viewLifecycleOwner, ::applyState)
    }


    private fun applyState(state: HistoryState){
        when (state) {
            HistoryState.Initial,
            HistoryState.Loading    -> applyLoadingState()

            is HistoryState.Content -> applyContentState(state)

            is HistoryState.Empty   -> applyEmptyState()

            is HistoryState.Error -> applyErrorState(state)
        }
    }

    private fun applyLoadingState() {
        binding.recyclerView.isVisible = false
        binding.progressBar.isVisible = true
        binding.newLoanFab.isVisible = false
        binding.errorLayout.root.isGone = true
        binding.emptyLayout.root.isGone = true
    }

    private fun applyContentState(state: HistoryState.Content) {
        binding.refreshLayout.isRefreshing = false
        binding.recyclerView.isVisible = true
        loanAdapter.items = state.loans
        binding.progressBar.isVisible = false
        binding.newLoanFab.isVisible = true
        binding.errorLayout.root.isGone = true
        binding.emptyLayout.root.isGone = true
    }

    private fun applyEmptyState() {
        binding.refreshLayout.isRefreshing = false
        binding.recyclerView.isVisible = false
        binding.progressBar.isVisible = false
        binding.newLoanFab.isVisible = true
        binding.errorLayout.root.isGone = true
        binding.emptyLayout.root.isVisible = true
    }

    private fun applyErrorState(state: HistoryState.Error) {
        binding.refreshLayout.isRefreshing = false
        binding.recyclerView.isVisible = false
        binding.progressBar.isVisible = false
        binding.newLoanFab.isVisible = false
        binding.errorLayout.root.isVisible = true
        binding.errorLayout.errorBodyTextView.text = state.error.getErrorMessage(requireContext())
        binding.emptyLayout.root.isGone = true
        binding.errorLayout.reconnectButton.setOnClickListener { viewModel.loadData() }
    }

    private fun showDialog() {
        val hasShown: Boolean = sharedPreferences.getBoolean("INSTRUCTIONS_SHOWN", false)
        if (!hasShown) showInstructionsDialog(requireContext())
        sharedPreferences.edit().putBoolean("INSTRUCTIONS_SHOWN", true).apply()
    }
}