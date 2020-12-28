package com.deepco.studyfork

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.deepco.data.data.model.RecentSearchData
import com.deepco.studyfork.databinding.RecentSearchItemBinding
import com.deepco.studyfork.viewmodel.RecentSearchViewModel

class RecentSearchRecyclerAdapter(
    private val recentSearchViewModel: RecentSearchViewModel
) :
    RecyclerView.Adapter<RecentSearchViewHolder>() {

    private var queryItem = ArrayList<RecentSearchData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchViewHolder {
        val binding =
            DataBindingUtil.inflate<RecentSearchItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.recent_search_item,
                parent,
                false
            )
        binding.viewModel = recentSearchViewModel
        return RecentSearchViewHolder(binding)
    }

    override fun getItemCount() = queryItem.count()

    override fun onBindViewHolder(holder: RecentSearchViewHolder, position: Int) {
        holder.bind(this.queryItem[position])
    }

    fun setItemList(queryItem: List<RecentSearchData>) {
        this.queryItem.clear()
        this.queryItem.addAll(queryItem)
        notifyDataSetChanged()
    }

    fun clear() {
        this.queryItem.clear()
        notifyDataSetChanged()
    }
}