package com.example.architecturestudy.ui.blog

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturestudy.R
import com.example.architecturestudy.model.BlogItems
import kotlinx.android.synthetic.main.item_blog.view.*


class BlogAdapter(val items : List<BlogItems>) : RecyclerView.Adapter<BlogAdapter.BlogHolder>(){

    private val blogList : MutableList<BlogItems> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_blog, parent, false)
        return BlogHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size

    }


    override fun onBindViewHolder(holder: BlogHolder, position: Int) {
        holder.bind(items[position])
    }

    fun upDate(blogList : List<BlogItems>) {
        this.blogList.clear()
        this.blogList.addAll(blogList)
        notifyDataSetChanged()
    }


    class BlogHolder(view : View) : RecyclerView.ViewHolder(view)  {

        fun bind(item : BlogItems) {
            with(itemView) {
                blog_title.text = HtmlCompat.fromHtml(item.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
                Log.i("Blog", "title =${blog_title.text}")
                blog_contents.text = HtmlCompat.fromHtml(item.description, HtmlCompat.FROM_HTML_MODE_COMPACT)
                Log.i("Blog", "contents =${blog_title.text}")

                blog_owner.text = item.bloggername
                Log.i("Blog", "contents =${blog_title.text}")

                blog_date.text = item.postdate

            }
        }

    }

}