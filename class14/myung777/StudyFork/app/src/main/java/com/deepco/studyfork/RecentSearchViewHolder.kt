package com.deepco.studyfork

import androidx.recyclerview.widget.RecyclerView
import com.deepco.data.data.model.RecentSearchData
import com.deepco.studyfork.databinding.RecentSearchItemBinding

class RecentSearchViewHolder(
    private val binding: RecentSearchItemBinding
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(queryItem: RecentSearchData) {
        binding.recentSearch = queryItem
        binding.executePendingBindings()
    }
}