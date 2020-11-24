package com.deepco.studyfork

import androidx.recyclerview.widget.RecyclerView
import com.deepco.studyfork.databinding.RecentSearchItemBinding

class RecentSearchViewHolder(
    private val binding: RecentSearchItemBinding
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(queryItem: String) {
        binding.title.text = queryItem
        binding.executePendingBindings()
    }
}