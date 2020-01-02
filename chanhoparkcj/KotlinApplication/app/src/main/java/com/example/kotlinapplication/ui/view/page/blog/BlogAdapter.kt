package com.example.kotlinapplication.ui.view.page.blog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.R
import com.example.kotlinapplication.data.model.BlogItem
import com.example.kotlinapplication.extension.getHtmlText
import com.example.kotlinapplication.ui.base.BaseAdapter
import kotlinx.android.synthetic.main.item_blog.view.*

class BlogAdapter(private val listener: (BlogItem) -> Unit) :
    BaseAdapter<BlogItem, BlogAdapter.BlogViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder =
        BlogViewHolder(parent, listener)

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) =
        holder.bind(items[position])

    class BlogViewHolder(parent: ViewGroup, private val listener: (BlogItem) -> Unit) :
        RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_blog,
                parent,
                false
            )
        ) {
        private lateinit var item: BlogItem

        init {
            itemView.setOnClickListener {
                listener(item)
            }
        }

        fun bind(item: BlogItem) {
            this.item = item
            with(itemView) {
                textview_blog_title.text = item.title.getHtmlText()
                textview_blog_bloggername.text = item.bloggername.getHtmlText()
                textview_blog_bloggerlink.text = item.bloggerlink.getHtmlText()
                textview_blog_description.text = item.description.getHtmlText()
            }
        }
    }
}