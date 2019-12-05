package com.example.naversearchapistudy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BlogAdapter(val items: List<BlogItems>) : RecyclerView.Adapter<BlogAdapter.blogHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): blogHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_blog, parent, false)
        return blogHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: blogHolder, position: Int) {
        holder.bind(items[position])
    }


    class blogHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val title = itemView.findViewById<TextView>(R.id.blog_title)
        val content = itemView.findViewById<TextView>(R.id.blog_title)
        val blogger = itemView.findViewById<TextView>(R.id.blog_blogger)


        fun bind(BlogData: BlogItems) {
            title.text = BlogData.title
            content.text = BlogData.contents
            blogger.text = BlogData.blogger
        }
    }
}