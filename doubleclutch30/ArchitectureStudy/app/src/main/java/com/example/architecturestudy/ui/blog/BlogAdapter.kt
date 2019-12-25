package com.example.architecturestudy.ui.blog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturestudy.R
import com.example.architecturestudy.data.model.BlogItem
import com.example.architecturestudy.ui.startWebView
import kotlinx.android.synthetic.main.item_blog.view.*


class BlogAdapter : RecyclerView.Adapter<BlogAdapter.BlogHolder>(){

    private val blogItem : MutableList<BlogItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_blog, parent, false)
        return BlogHolder(view).apply {
            itemView.setOnClickListener { v -> v.startWebView(blogItem[adapterPosition].link) }
        }
    }

    override fun getItemCount(): Int {
        return blogItem.size
    }

    override fun onBindViewHolder(holder: BlogHolder, position: Int) {
        holder.bind(blogItem[position])
    }

    fun update(blogList : List<BlogItem>) {
        this.blogItem.clear()
        this.blogItem.addAll(blogList)
        notifyDataSetChanged()
    }

    class BlogHolder(view : View) : RecyclerView.ViewHolder(view)  {

        fun bind(item : BlogItem) {
            with(itemView) {
                blog_title.text = HtmlCompat.fromHtml(item.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
                blog_contents.text = HtmlCompat.fromHtml(item.description, HtmlCompat.FROM_HTML_MODE_COMPACT)
                blog_owner.text = item.bloggername
                blog_date.text = item.postdate
            }
        }

    }

}