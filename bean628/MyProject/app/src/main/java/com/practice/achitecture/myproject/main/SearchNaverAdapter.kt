package com.practice.achitecture.myproject.main

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.practice.achitecture.myproject.R
import com.practice.achitecture.myproject.databinding.ItemBookAndMovieBinding
import com.practice.achitecture.myproject.enums.SearchType
import com.practice.achitecture.myproject.model.SearchedItem

class SearchNaverAdapter :
    RecyclerView.Adapter<SearchNaverAdapter.ViewHolder>() {

    private val items: ArrayList<SearchedItem> = ArrayList()
    var searchType: SearchType = SearchType.MOVIE

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
        if (!newItems.isNullOrEmpty()) {
            items.addAll(newItems)
        }
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
                    root.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(data.link)))
                }
                when (searchType) {
                    SearchType.MOVIE, SearchType.BOOK -> ivMainImage.visibility = View.VISIBLE
                    SearchType.BLOG, SearchType.NEWS -> ivMainImage.visibility = View.GONE
                }
                executePendingBindings()
            }
        }
    }
}