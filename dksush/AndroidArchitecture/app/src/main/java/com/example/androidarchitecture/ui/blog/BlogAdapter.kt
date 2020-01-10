package com.example.androidarchitecture.ui.blog

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.androidarchitecture.common.StringConst
import com.example.androidarchitecture.data.response.BlogData
import com.example.androidarchitecture.databinding.ItemBlogBinding
import com.example.androidarchitecture.ui.WebviewActivity
import com.example.androidarchitecture.ui.base.BaseRecyclerAdapter
import com.example.androidarchitecture.ui.base.BaseViewHolder


class BlogAdapter : BaseRecyclerAdapter<BlogData, BlogAdapter.BlogHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BlogHolder(
        ItemBlogBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    inner class BlogHolder(private val binding: ItemBlogBinding) :
        BaseViewHolder<BlogData>(binding.root) {
        private lateinit var item: BlogData

        init {
            binding.setOnClick {
                Intent(it.context, WebviewActivity::class.java).apply {
                    putExtra(StringConst.INTENT_KEY_LINK, item.link)
                }.run { it.context.startActivity(this) }
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