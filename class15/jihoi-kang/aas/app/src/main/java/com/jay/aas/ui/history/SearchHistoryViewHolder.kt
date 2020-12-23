package com.jay.aas.ui.history

import androidx.recyclerview.widget.RecyclerView
import com.jay.aas.databinding.ItemSearchHistoryBinding
import com.jay.data.model.SearchHistory

class SearchHistoryViewHolder(
    private val binding: ItemSearchHistoryBinding,
    private val onItemClick: (String) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            binding.item?.queryText?.let(onItemClick)
        }
    }

    fun onBind(searchHistory: SearchHistory) {
        binding.item = searchHistory
        binding.executePendingBindings()
    }

}