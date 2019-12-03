package com.example.androidarchitecture.ui.blog

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidarchitecture.R
import com.example.androidarchitecture.models.ResponseBlog
import com.example.androidarchitecture.ui.WebviewActivity
import kotlinx.android.synthetic.main.item_blog.view.*


class blogAdapter(
    val items: List<ResponseBlog>
) : RecyclerView.Adapter<blogAdapter.blogHolder>() {

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

    inner class blogHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(model: ResponseBlog) {
            with(view) {
                blog_title.text = model.title
                blog_description.text = model.description
                blog_owner.text = model.bloggerName
                blog_postdate.text = model.postdate

                setOnClickListener() {
                    Intent(context, WebviewActivity::class.java).apply {
                        putExtra("link", model.link)
                    }.run { context.startActivity(this) }
                }
            }
        }

    }

}