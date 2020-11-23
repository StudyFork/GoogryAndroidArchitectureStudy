package com.jay.aas.ui.history

import androidx.recyclerview.widget.RecyclerView
import com.jay.aas.databinding.ItemSearchHistoryBinding
import com.jay.aas.model.SearchHistory

class SearchHistoryViewHolder(
    private val binding: ItemSearchHistoryBinding,
    private val onItemClick: (String) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    private var searchHistory: SearchHistory? = null

    init {
        binding.root.setOnClickListener {
            searchHistory?.queryText?.let(onItemClick)
        }
    }

    fun onBind(searchHistory: SearchHistory) {
        this.searchHistory = searchHistory
        binding.item = searchHistory
        binding.executePendingBindings()
    }

}