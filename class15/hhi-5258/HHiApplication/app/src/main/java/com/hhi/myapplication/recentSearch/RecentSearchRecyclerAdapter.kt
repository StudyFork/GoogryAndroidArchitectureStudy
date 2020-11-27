package com.hhi.myapplication.recentSearch

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hhi.myapplication.databinding.RecentSearchRecyclerItemBinding
import com.hhi.myapplication.main.MainActivity
import kotlinx.android.synthetic.main.recent_search_recycler_item.view.*

class RecentSearchRecyclerAdapter : RecyclerView.Adapter<RecentSearchRecyclerAdapter.ViewHolder>() {
    private val queryList = mutableListOf<String>()

    fun setQueryList(list: List<String>) {
        with(this.queryList) {
            clear()
            addAll(list.asReversed())
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecentSearchRecyclerAdapter.ViewHolder {
        val binding = RecentSearchRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return queryList.size
    }

    override fun onBindViewHolder(holder: RecentSearchRecyclerAdapter.ViewHolder, position: Int) {
        val query = queryList[position]
        val listener = View.OnClickListener {
            val intent = Intent(it.context, MainActivity::class.java)
            intent.putExtra("query", query)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            it.context.startActivity(intent)
        }
        holder.bind(listener, query)
    }

    class ViewHolder(private val binding: RecentSearchRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: View.OnClickListener, query: String) {
            binding.recentItemTextTitle.text = query
            binding.recentItemTextTitle.setOnClickListener(listener)
        }
    }
}