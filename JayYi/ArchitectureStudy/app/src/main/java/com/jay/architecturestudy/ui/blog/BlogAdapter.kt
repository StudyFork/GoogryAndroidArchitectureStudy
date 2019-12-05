package com.jay.architecturestudy.ui.blog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import com.jay.architecturestudy.R
import com.jay.architecturestudy.model.Blog
import com.jay.architecturestudy.ui.BaseAdapter
import com.jay.architecturestudy.ui.BaseViewHolder
import kotlinx.android.synthetic.main.list_item_blog.view.*


internal class BlogAdapter : BaseAdapter<Blog, BlogHolder>() {
    private val data = arrayListOf<Blog>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_blog, parent, false)
        return BlogHolder(view)
    }

    override fun setData(items: List<Blog>) {
        super.setData(items)
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }
}

internal class BlogHolder(
    view: View
) : BaseViewHolder<Blog>(view) {

    override fun bind(item: Blog) {
        with(itemView) {
            blog_title.text = HtmlCompat.fromHtml(item.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
            blog_description.text =
                HtmlCompat.fromHtml(item.description, HtmlCompat.FROM_HTML_MODE_COMPACT)
            blog_owner.text = item.bloggerName
            blog_postdate.text = item.postdate
        }
    }
}