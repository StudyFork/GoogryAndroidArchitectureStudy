package com.example.architecturestudy.ui.blog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturestudy.R
import com.example.architecturestudy.data.model.BlogItem
import com.example.architecturestudy.databinding.ItemBlogBinding
import com.example.architecturestudy.ui.startWebView


class BlogAdapter : RecyclerView.Adapter<BlogAdapter.BlogHolder>() {

    private val blogItem: MutableList<BlogItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogHolder {
        val binding = DataBindingUtil.inflate<ItemBlogBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_blog,
            parent,
            false
        )

        return BlogHolder(binding).apply {
            itemView.setOnClickListener { v -> v.startWebView(blogItem[adapterPosition].link) }
        }
    }

    override fun getItemCount(): Int {
        return blogItem.size
    }

    override fun onBindViewHolder(holder: BlogHolder, position: Int) {
        holder.bind(blogItem[position])
    }

    fun update(blogList: List<BlogItem>) {
        this.blogItem.clear()
        this.blogItem.addAll(blogList)
        notifyDataSetChanged()
    }

    class BlogHolder(private val binding: ItemBlogBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BlogItem) {
            binding.blog = item
        }
    }
}