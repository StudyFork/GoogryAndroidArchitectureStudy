package com.example.androidarchitecture.ui.blog

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.androidarchitecture.R
import com.example.androidarchitecture.data.response.BlogData
import com.example.androidarchitecture.ui.WebviewActivity
import com.example.androidarchitecture.ui.base.BaseRecyclerAdapter
import com.example.androidarchitecture.ui.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_blog.view.*


class BlogAdapter : BaseRecyclerAdapter<BlogData, BlogAdapter.BlogHolder>(DiffCallback()) {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_blog, parent, false)
        this.context = parent.context
        return BlogHolder(view)
    }

    inner class BlogHolder(view: View) : BaseViewHolder<BlogData>(view) {
        lateinit var item: BlogData

        init {
            view.setOnClickListener {
                Intent(context, WebviewActivity::class.java).apply {
                    putExtra("link", item.link)
                }.run { context.startActivity(this) }
            }
        }

        override fun bind(item: BlogData) {
            this.item = item
            with(itemView) {
                blog_title.text = item.title
                blog_description.text = item.description
                blog_owner.text = item.bloggerName
                blog_postdate.text = item.postdate

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