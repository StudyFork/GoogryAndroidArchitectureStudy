package com.example.androidarchitecture.ui.blog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidarchitecture.R
import com.example.androidarchitecture.models.ResponseBlog
import kotlinx.android.synthetic.main.item_blog.view.*


class blogAdapter(
    val items: List<ResponseBlog>,
    val mContext: Context,
    val mOnItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<blogAdapter.blogHolder>() {

    interface OnItemClickListener {
        fun onItemClick(link: String)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): blogHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_blog, parent, false)
        return blogHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: blogHolder, position: Int) {
        val blog_item = items.get(position)

        holder.itemView.blog_title.text = blog_item.title
        holder.itemView.blog_description.text = blog_item.description
        holder.itemView.blog_owner.text = blog_item.bloggerName
        holder.itemView.blog_postdate.text = blog_item.postdate

        holder.itemView.setOnClickListener() {
            mOnItemClickListener.onItemClick(blog_item.link)
        }


    }

    inner class blogHolder(view: View) : RecyclerView.ViewHolder(view)

}