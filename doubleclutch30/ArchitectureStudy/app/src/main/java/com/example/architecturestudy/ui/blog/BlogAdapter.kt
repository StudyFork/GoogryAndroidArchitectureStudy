package com.example.architecturestudy.ui.blog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturestudy.R
import com.example.architecturestudy.model.BlogItems
import com.example.architecturestudy.ui.startWebView
import kotlinx.android.synthetic.main.item_blog.view.*


class BlogAdapter : RecyclerView.Adapter<BlogAdapter.BlogHolder>(){

    private val blogItem : MutableList<BlogItems> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_blog, parent, false)
        return BlogHolder(view)
    }

    override fun getItemCount(): Int {
        return blogItem.size

    }

    override fun onBindViewHolder(holder: BlogHolder, position: Int) {
        holder.bind(blogItem[position])
    }

    fun update(blogList : List<BlogItems>) {
        this.blogItem.clear()
        this.blogItem.addAll(blogList)
        notifyDataSetChanged()
    }


    class BlogHolder(view : View) : RecyclerView.ViewHolder(view)  {

        lateinit var item : BlogItems

        init {
            itemView.setOnClickListener { view ->
                view.startWebView(item.link)
            }
        }

        fun bind(item : BlogItems) {
            this.item = item

            with(itemView) {
                blog_title.text = HtmlCompat.fromHtml(item.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
                blog_contents.text = HtmlCompat.fromHtml(item.description, HtmlCompat.FROM_HTML_MODE_COMPACT)
                blog_owner.text = item.bloggername
                blog_date.text = item.postdate
            }
        }

    }

}