package com.example.kotlinapplication.adapter.viewholder

import android.os.Build
import android.text.Html
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.adapter.ListBlogAdapter
import com.example.kotlinapplication.model.BlogItems
import kotlinx.android.synthetic.main.blog_list_item.view.*

class BlogViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: BlogItems, listener: ListBlogAdapter.ItemListener?) {

        itemView.blogger_item_layout.setOnClickListener {
            listener?.let {
                it.onBlogItemClick(item)
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            itemView.blogger_item_title.text = Html.fromHtml(item.title, 0)
            itemView.blogger_item_bloggername.text = Html.fromHtml(item.bloggername, 0)
            itemView.blogger_item_bloggerlink.text = Html.fromHtml(item.bloggerlink, 0)
            itemView.blogger_item_description.text = Html.fromHtml(item.description, 0)
        } else {
            itemView.blogger_item_description.text = item.description
            itemView.blogger_item_title.text = item.title
            itemView.blogger_item_bloggername.text = item.bloggername
            itemView.blogger_item_bloggerlink.text = item.bloggerlink

        }


    }
}