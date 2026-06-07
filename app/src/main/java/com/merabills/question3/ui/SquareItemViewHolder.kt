package com.merabills.question3.ui

import androidx.recyclerview.widget.RecyclerView
import com.merabills.question3.databinding.ItemSquareBinding
import com.merabills.question3.models.SquareItemData

class SquareItemViewHolder(
    val binding: ItemSquareBinding,
    onSquareClick: (SquareItemData) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var currentItem: SquareItemData? = null

    init {
        binding.root.setOnClickListener {
            currentItem?.let(onSquareClick)
        }
    }

    fun bind(squareItemData: SquareItemData) {
        currentItem = squareItemData
        binding.tvSquare.text = squareItemData.index
        binding.tvSquare.setBackgroundColor(squareItemData.color)

    }

}