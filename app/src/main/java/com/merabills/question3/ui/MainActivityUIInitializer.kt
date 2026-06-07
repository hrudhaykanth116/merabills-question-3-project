package com.merabills.question3.ui

import androidx.recyclerview.widget.LinearLayoutManager
import com.merabills.question3.constants.UIConfig
import com.merabills.question3.databinding.ActivityMainBinding

fun ActivityMainBinding.initializeUI(rowAdapter: RowsAdapter) {
    rvRows.apply {
        adapter = rowAdapter
        layoutManager = LinearLayoutManager(context).apply {
            initialPrefetchItemCount = UIConfig.ROWS_RV_ITEM_PREFETCH_COUNT
        }
        setHasFixedSize(true)
        setItemViewCacheSize(UIConfig.ROWS_RV_ITEM_CACHE_SIZE)
        if(UIConfig.shouldDisableAnimation) itemAnimator = null
    }
}