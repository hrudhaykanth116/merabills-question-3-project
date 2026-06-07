package com.merabills.question3.ui

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.merabills.question3.databinding.ItemSquareBinding
import com.merabills.question3.models.SquareItemData

class SquaresAdapter(
    private val onSquareClick: (SquareItemData) -> Unit
) : ListAdapter<SquareItemData, SquareItemViewHolder>(SquareItemDiffCallback()) {


    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SquareItemViewHolder {
        val binding = ItemSquareBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SquareItemViewHolder(binding, onSquareClick)
    }

    override fun onBindViewHolder(
        holder: SquareItemViewHolder,
        position: Int,
    ) {
        holder.bind(getItem(position))
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).index.toLong()
    }

}