package com.jay.aas.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jay.aas.R
import com.jay.aas.databinding.ItemSearchHistoryBinding
import com.jay.aas.model.SearchHistory

class SearchHistoryAdapter(
    private val onItemClick: (String) -> Unit,
) : RecyclerView.Adapter<SearchHistoryViewHolder>() {

    private val searchHistories: MutableList<SearchHistory> = mutableListOf()

    fun setSearchHistories(searchHistories: List<SearchHistory>) {
        this.searchHistories.clear()
        this.searchHistories.addAll(searchHistories)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryViewHolder {
        val binding =
            DataBindingUtil.inflate<ItemSearchHistoryBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_search_history,
                parent,
                false
            )
        return SearchHistoryViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: SearchHistoryViewHolder, position: Int) {
        holder.onBind(searchHistories[position])
    }

    override fun getItemCount(): Int = searchHistories.size

}