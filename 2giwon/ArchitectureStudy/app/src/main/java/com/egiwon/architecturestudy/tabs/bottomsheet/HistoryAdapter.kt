package com.egiwon.architecturestudy.tabs.bottomsheet

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.egiwon.architecturestudy.R
import com.egiwon.architecturestudy.base.BaseRecyclerView
import com.egiwon.architecturestudy.databinding.SearchHistoryItemBinding

class HistoryAdapter(
    private val onClick: (String) -> Unit,
    @LayoutRes private val layoutRes: Int = R.layout.search_history_item
) : BaseRecyclerView.Adapter<String, SearchHistoryItemBinding>(
    layoutRes
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder =
        HistoryViewHolder(parent).apply {
            itemView.setOnClickListener {
                onClick(getItem(adapterPosition) ?: "")
            }
        }

    inner class HistoryViewHolder(
        parent: ViewGroup
    ) : BaseRecyclerView.BaseViewHolder<SearchHistoryItemBinding>(
        parent,
        layoutRes
    ) {

        override fun onBindViewHolder(item: Any?) {
            binding.query.text = (item as? String) ?: ""
            super.onBindViewHolder(item)
        }
    }

}


