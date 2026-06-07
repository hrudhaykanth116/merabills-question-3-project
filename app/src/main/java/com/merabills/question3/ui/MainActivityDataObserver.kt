package com.merabills.question3.ui

import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.merabills.question3.MainActivity
import com.merabills.question3.MainViewModel
import com.merabills.question3.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

fun MainActivity.observeData(
    viewModel: MainViewModel,
    binding: ActivityMainBinding,
    rowAdapter: RowsAdapter
) {

    lifecycleScope.launch {

        repeatOnLifecycle(Lifecycle.State.STARTED) {

            viewModel.uiState.collectLatest { uiState ->
                // TODO: Consider showing progress bar if data fetch takes longer time.
                if (uiState.isLoading || uiState.rowsData.isEmpty()) {
                    binding.rvRows.isVisible = false
                } else {
                    binding.rvRows.isVisible = true
                    rowAdapter.submitList(uiState.rowsData)
                }
            }
        }
    }
}