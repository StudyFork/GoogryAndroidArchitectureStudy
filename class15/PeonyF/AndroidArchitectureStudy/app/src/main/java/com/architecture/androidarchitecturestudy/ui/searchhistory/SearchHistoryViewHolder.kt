package com.architecture.androidarchitecturestudy.ui.searchhistory

import androidx.recyclerview.widget.RecyclerView
import com.architecture.androidarchitecturestudy.data.model.SearchHistoryEntity
import com.architecture.androidarchitecturestudy.databinding.SearchHistoryItemBinding

class SearchHistoryViewHolder(
    private val binding: SearchHistoryItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(searchHistory: SearchHistoryEntity) {
        binding.item = searchHistory
        binding.executePendingBindings()
    }
}
