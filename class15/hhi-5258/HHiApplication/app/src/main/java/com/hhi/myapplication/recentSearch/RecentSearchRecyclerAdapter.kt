package com.hhi.myapplication.recentSearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hhi.myapplication.R
import kotlinx.android.synthetic.main.recent_search_recycler_item.view.*

class RecentSearchRecyclerAdapter : RecyclerView.Adapter<RecentSearchRecyclerAdapter.ViewHolder>() {
    private val queryList = mutableListOf<String>()

    fun setQueryList(list: List<String>) {
        this.queryList.clear()
        this.queryList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecentSearchRecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.recent_search_recycler_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return queryList.size
    }

    override fun onBindViewHolder(holder: RecentSearchRecyclerAdapter.ViewHolder, position: Int) {
        holder.bind(queryList[position])
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        fun bind(query: String) {
            itemView.recent_item_text_title.text = query
        }
    }


}