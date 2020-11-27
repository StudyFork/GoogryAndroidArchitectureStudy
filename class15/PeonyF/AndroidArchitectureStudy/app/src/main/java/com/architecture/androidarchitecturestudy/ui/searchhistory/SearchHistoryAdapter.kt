package com.architecture.androidarchitecturestudy.ui.searchhistory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.architecture.androidarchitecturestudy.R
import com.architecture.androidarchitecturestudy.data.model.SearchHistoryEntity
import com.architecture.androidarchitecturestudy.databinding.SearchHistoryItemBinding

class SearchHistoryAdapter(
    private val onItemClick: (String) -> Unit,
) : RecyclerView.Adapter<SearchHistoryViewHolder>() {

    private var searchHistory = ArrayList<SearchHistoryEntity>()

    fun setSearchHistories(searchHistories: List<SearchHistoryEntity>) {
        this.searchHistory.clear()
        this.searchHistory.addAll(searchHistories)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SearchHistoryViewHolder(
            DataBindingUtil.inflate<SearchHistoryItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.search_history_item,
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener {
                onItemClick(searchHistory[adapterPosition].searchHistoryKeyword)
            }
        }

    override fun onBindViewHolder(holder: SearchHistoryViewHolder, position: Int) {
        holder.onBind(searchHistory[position])
    }

    override fun getItemCount(): Int = searchHistory.size
}