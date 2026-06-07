package com.merabills.question3.ui

import com.merabills.question3.models.SquareItemData

class SquareItemDiffCallback : androidx.recyclerview.widget.DiffUtil.ItemCallback<SquareItemData>() {

    override fun areItemsTheSame(oldItem: SquareItemData, newItem: SquareItemData): Boolean {
        return oldItem.index == newItem.index
    }

    override fun areContentsTheSame(oldItem: SquareItemData, newItem: SquareItemData): Boolean {
        return oldItem == newItem
    }
}