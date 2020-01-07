package com.example.kotlinapplication.ui.view.page.blog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.R
import com.example.kotlinapplication.data.model.BlogItem
import com.example.kotlinapplication.databinding.ItemBlogBinding
import com.example.kotlinapplication.ui.base.BaseAdapter

class BlogAdapter(private val listener: (BlogItem) -> Unit) :
    BaseAdapter<BlogItem, BlogAdapter.BlogViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemBlogBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_blog, parent, false)
        return BlogViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) =
        holder.bind(items[position])


    inner class BlogViewHolder(
        private val binding: ItemBlogBinding,
        private val listener: (BlogItem) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var item: BlogItem

        init {
            itemView.setOnClickListener {
                listener(item)
            }
        }

        fun bind(item: BlogItem) {
            this.item = item
            binding.blog = item
        }
    }
}