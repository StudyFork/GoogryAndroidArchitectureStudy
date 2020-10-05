package com.hjhan.hyejeong.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hjhan.hyejeong.R
import com.hjhan.hyejeong.databinding.ItemQueryBinding

class QueryHistoryAdapter :
    RecyclerView.Adapter<QueryHistoryAdapter.QueryViewHolder>() {

    private var list = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueryViewHolder {
        val view = DataBindingUtil.inflate<ItemQueryBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_query,
            parent,
            false
        )

        return QueryViewHolder(view)
    }

    override fun onBindViewHolder(holder: QueryViewHolder, position: Int) {

        holder.bind(list[position])

    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setMovieList(list: List<String>) {
        this.list = list.toMutableList()

        notifyDataSetChanged()
    }

    class QueryViewHolder(private val binding: ItemQueryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: String) {
            binding.queryTextView.text = data
        }
    }
}