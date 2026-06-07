package com.merabills.question3.ui

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.merabills.question3.constants.UIConfig
import com.merabills.question3.databinding.ItemRowBinding
import com.merabills.question3.models.RowItemData
import com.merabills.question3.models.SquareItemData

class RowsAdapter(
    private val onSquareClick: (SquareItemData) -> Unit
) : ListAdapter<RowItemData, RowItemViewHolder>(
    RowItemDiffUtilCallback()
) {

    // TODO: Consider viewmodel to save scroll states if needed, currently saving in adapter for simplicity
    private val scrollStates = mutableMapOf<Int, Parcelable?>()

    init {
        setHasStableIds(true)
    }

    private val squaresViewPool = RecyclerView.RecycledViewPool().apply {
        setMaxRecycledViews(0, UIConfig.SQUARES_MAX_RECYCLABLE_VIEWS)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowItemViewHolder {
        val binding = ItemRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RowItemViewHolder(binding, onSquareClick, squaresViewPool)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RowItemViewHolder, position: Int, payloads: List<Any?>) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            // This mainly avoided the case where deleted square item was rendering whole row again since row has changed.
            holder.updateSquares(payloads.last() as List<SquareItemData>)
        }
    }

    override fun onBindViewHolder(
        holder: RowItemViewHolder,
        position: Int,
    ) {
        val item = getItem(position)
        val savedState = scrollStates[item.rowIndex]
        holder.bind(item, savedState)
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).rowIndex.toLong()
    }

    override fun onViewRecycled(holder: RowItemViewHolder) {
        super.onViewRecycled(holder)

        val adapterPosition = holder.bindingAdapterPosition
        if (adapterPosition != RecyclerView.NO_POSITION) {
            val rowIndex = getItem(adapterPosition).rowIndex
            scrollStates[rowIndex] = holder.binding.rvSquares.layoutManager?.onSaveInstanceState()
        }
    }



}