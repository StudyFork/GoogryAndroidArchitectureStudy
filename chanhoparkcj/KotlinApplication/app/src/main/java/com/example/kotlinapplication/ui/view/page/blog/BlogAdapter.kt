package com.example.kotlinapplication.ui.view.page.blog

import android.view.ViewGroup
import com.example.kotlinapplication.data.model.BlogItem
import com.example.kotlinapplication.ui.base.BaseAdapter

class BlogAdapter(private val listener: (BlogItem) -> Unit) :
    BaseAdapter<BlogItem, BlogViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder =
        BlogViewHolder(parent, listener)

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) =
        holder.bind(items[position])
}