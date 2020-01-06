package com.example.androidarchitecture.ui.blog

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.androidarchitecture.data.response.BlogData
import com.example.androidarchitecture.databinding.ItemBlogBinding
import com.example.androidarchitecture.ui.WebviewActivity
import com.example.androidarchitecture.ui.base.BaseRecyclerAdapter
import com.example.androidarchitecture.ui.base.BaseViewHolder


class BlogAdapter : BaseRecyclerAdapter<BlogData, BlogAdapter.BlogHolder>(DiffCallback()) {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogHolder {
        context = parent.context
        return BlogHolder(ItemBlogBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    inner class BlogHolder(private val binding: ItemBlogBinding) :
        BaseViewHolder<BlogData>(binding.root) {
        lateinit var item: BlogData

        init {
            binding.setOnClick {
                Intent(context, WebviewActivity::class.java).apply {
                    putExtra("link", item.link)
                }.run { context.startActivity(this) }
            }
        }

        override fun bind(item: BlogData) {
            this.item = item
            with(binding) {
                items = item
                executePendingBindings()
            }
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<BlogData>() {
    override fun areItemsTheSame(oldItem: BlogData, newItem: BlogData): Boolean {
        return oldItem == newItem

    }

    override fun areContentsTheSame(oldItem: BlogData, newItem: BlogData): Boolean {
        return oldItem == newItem
    }

}