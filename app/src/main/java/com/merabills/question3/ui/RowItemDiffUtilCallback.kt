package com.merabills.question3.ui

import androidx.recyclerview.widget.DiffUtil
import com.merabills.question3.models.RowItemData

class RowItemDiffUtilCallback : DiffUtil.ItemCallback<RowItemData>() {

        override fun areItemsTheSame(
            oldItem: RowItemData,
            newItem: RowItemData
        ): Boolean {
            return oldItem.rowIndex == newItem.rowIndex
        }

        override fun areContentsTheSame(
            oldItem: RowItemData,
            newItem: RowItemData
        ): Boolean {
            return oldItem == newItem
        }

        override fun getChangePayload(oldItem: RowItemData, newItem: RowItemData): Any? {
            return newItem.squaresList
        }

    }