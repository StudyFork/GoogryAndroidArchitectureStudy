package com.example.studyapplication.main.blog.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studyapplication.R
import com.example.studyapplication.vo.BlogList
import kotlinx.android.synthetic.main.item_blog.view.*

class BlogAdapter(var arrBlogInfo : Array<BlogList.BlogInfo>) : RecyclerView.Adapter<BlogHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogHolder {
        val inflater : LayoutInflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view : View = inflater.inflate(R.layout.item_blog, parent, false)

        return BlogHolder(view)
    }

    override fun onBindViewHolder(holder: BlogHolder, position: Int) {
        holder.itemView.tvTitle.text = arrBlogInfo[position].title
        holder.itemView.tvDescription.text = arrBlogInfo[position].description
        holder.itemView.tvBloggername.text = arrBlogInfo[position].bloggername
    }

    override fun getItemCount(): Int {
        return arrBlogInfo.size
    }
}