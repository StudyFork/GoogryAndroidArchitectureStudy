package com.jay.architecturestudy.ui.blog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.jay.architecturestudy.R
import com.jay.architecturestudy.data.model.Blog
import com.jay.architecturestudy.databinding.ListItemBlogBinding
import com.jay.architecturestudy.ui.BaseAdapter
import com.jay.architecturestudy.ui.BaseViewHolder
import com.jay.architecturestudy.ui.OnItemClickListener
import com.jay.architecturestudy.util.startWebView


internal class BlogAdapter : BaseAdapter<Blog, BlogHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogHolder {
        val binding = ListItemBlogBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BlogHolder(binding)
    }

}

internal class BlogHolder(
    val binding: ListItemBlogBinding
) : BaseViewHolder<Blog>(binding), OnItemClickListener {

    init {
        binding.clickEvent = this
    }

    override fun bind(item: Blog) {
        with(binding) {
            blog = item
            executePendingBindings()
        }
    }

    override fun onClick(v: View, url: String) {
        v.startWebView(url)
    }
}