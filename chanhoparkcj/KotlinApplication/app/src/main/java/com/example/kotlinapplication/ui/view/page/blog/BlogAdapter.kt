package com.example.kotlinapplication.ui.view.page.blog

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.data.model.BlogItem

class BlogAdapter(private val listener: (BlogItem) -> Unit) :
    RecyclerView.Adapter<BlogViewHolder>() {
    private val items = mutableListOf<BlogItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder = BlogViewHolder(parent, listener)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) = holder.bind(items[position])

    fun resetItems(blogList: List<BlogItem>) {
        items.clear()
        items.addAll(blogList)
        notifyDataSetChanged()
    }
}