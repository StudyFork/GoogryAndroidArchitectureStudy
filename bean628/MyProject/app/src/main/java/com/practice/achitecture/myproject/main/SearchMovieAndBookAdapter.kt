package com.practice.achitecture.myproject.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.practice.achitecture.myproject.R
import com.practice.achitecture.myproject.databinding.ItemBookAndMovieBinding
import com.practice.achitecture.myproject.model.SearchedItem

class SearchMovieAndBookAdapter(private val searchedItemClickListener: SearchedItemClickListener) :
    RecyclerView.Adapter<SearchMovieAndBookAdapter.ViewHolder>() {

    private var items: ArrayList<SearchedItem> = ArrayList()

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.run {
            bind(item)
            itemView.tag = item
        }
    }

    fun notifyDataSetChanged(newItems: List<SearchedItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_book_and_movie,
                parent,
                false
            )
        )
    }

    inner class ViewHolder(private val binding: ItemBookAndMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: SearchedItem) {
            binding.run {
                item = data
                root.setOnClickListener {
                    searchedItemClickListener.onItemClick(items[this@ViewHolder.adapterPosition])
                }
                executePendingBindings()
            }
        }
    }
}