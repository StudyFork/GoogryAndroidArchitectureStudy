package com.jay.architecturestudy.ui.blog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.jay.architecturestudy.R
import com.jay.architecturestudy.data.model.Blog
import com.jay.architecturestudy.databinding.ListItemBlogBinding
import com.jay.architecturestudy.ui.BaseAdapter
import com.jay.architecturestudy.ui.BaseViewHolder
import com.jay.architecturestudy.util.startWebView


internal class BlogAdapter : BaseAdapter<Blog, BlogHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogHolder {
        val binding = DataBindingUtil.inflate<ListItemBlogBinding>(
            LayoutInflater.from(parent.context),
            R.layout.list_item_blog,
            parent,
            false
        )
        return BlogHolder(binding)
    }

}

internal class BlogHolder(
    val binding: ListItemBlogBinding
) : BaseViewHolder<Blog>(binding) {
    lateinit var item: Blog

    init {
        itemView.setOnClickListener { view ->
            view.startWebView(item.link)
        }
    }

    override fun bind(item: Blog) {
        this.item = item
        with(binding) {
            blog = item
            executePendingBindings()
        }
    }
}