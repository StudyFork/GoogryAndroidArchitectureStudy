package com.jay.architecturestudy.ui.blog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import com.jay.architecturestudy.R
import com.jay.architecturestudy.model.Blog
import com.jay.architecturestudy.ui.BaseAdapter
import com.jay.architecturestudy.ui.BaseViewHolder
import com.jay.architecturestudy.util.startWebView
import kotlinx.android.synthetic.main.list_item_blog.view.*


internal class BlogAdapter : BaseAdapter<Blog, BlogHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_blog, parent, false)
        return BlogHolder(view)
    }

}

internal class BlogHolder(
    view: View
) : BaseViewHolder<Blog>(view) {
    lateinit var item: Blog

    init {
        itemView.setOnClickListener { view ->
            view.startWebView(item.link)
        }
    }

    override fun bind(item: Blog) {
        this.item = item

        with(itemView) {
            blog_title.text = HtmlCompat.fromHtml(item.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
            blog_description.text =
                HtmlCompat.fromHtml(item.description, HtmlCompat.FROM_HTML_MODE_COMPACT)
            blog_owner.text = item.bloggerName
            blog_postdate.text = item.postdate
        }
    }
}