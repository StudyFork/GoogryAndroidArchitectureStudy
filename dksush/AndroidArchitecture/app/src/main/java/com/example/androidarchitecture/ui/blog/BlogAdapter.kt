package com.example.androidarchitecture.ui.blog

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidarchitecture.R
import com.example.androidarchitecture.models.BlogData
import com.example.androidarchitecture.ui.WebviewActivity
import kotlinx.android.synthetic.main.item_blog.view.*


class BlogAdapter : RecyclerView.Adapter<BlogAdapter.BlogHolder>() {

    private val data = arrayListOf<BlogData>()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_blog, parent, false)
        this.context = parent.context
        return BlogHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: BlogHolder, position: Int) {
        holder.bind(data[position])


    }

    fun setData(items: List<BlogData>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    inner class BlogHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        lateinit var model: BlogData

        init {
            view.setOnClickListener {
                Intent(context, WebviewActivity::class.java).apply {
                    putExtra("link", model.link)
                }.run { context.startActivity(this) }
            }
        }


        fun bind(model: BlogData) {
            this.model = model
            with(view) {
                blog_title.text = model.title
                blog_description.text = model.description
                blog_owner.text = model.bloggerName
                blog_postdate.text = model.postdate

            }
        }

    }
}