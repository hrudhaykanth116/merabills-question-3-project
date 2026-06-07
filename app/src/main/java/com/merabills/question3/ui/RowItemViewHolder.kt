package com.merabills.question3.ui

import android.os.Parcelable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.merabills.question3.constants.UIConfig
import com.merabills.question3.databinding.ItemRowBinding
import com.merabills.question3.models.RowItemData
import com.merabills.question3.models.SquareItemData

class RowItemViewHolder(
    val binding: ItemRowBinding,
    onSquareClick: (SquareItemData) -> Unit,
    squareViewPool: RecyclerView.RecycledViewPool
) : RecyclerView.ViewHolder(binding.root) {

    private val squareAdapter = SquaresAdapter(onSquareClick)

    init {

        binding.rvSquares.apply {
            adapter = squareAdapter
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL, false
            ).apply {
                initialPrefetchItemCount = UIConfig.SQUARES_RV_ITEM_PREFETCH_COUNT
            }

            if (UIConfig.shouldDisableAnimation) itemAnimator = null
            setHasFixedSize(true)
            setItemViewCacheSize(UIConfig.SQUARES_RV_ITEM_CACHE_SIZE)
            setRecycledViewPool(squareViewPool)
            isNestedScrollingEnabled = false
        }

    }


    fun bind(item: RowItemData, savedState: Parcelable?) {
        squareAdapter.submitList(item.squaresList){
            // Restore the scroll state after list is submitted, so that state is preserved as user scrolls.
            binding.rvSquares.layoutManager?.onRestoreInstanceState(savedState)
        }
    }

    fun updateSquares(squares: List<SquareItemData>) {
        squareAdapter.submitList(squares)
    }

}