package com.jay.architecturestudy.ui.blog

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.jay.architecturestudy.R
import com.jay.architecturestudy.model.Blog
import com.jay.architecturestudy.ui.WebViewActivity
import kotlinx.android.synthetic.main.list_item_blog.view.*


internal class BlogAdapter(private val context: Context) : RecyclerView.Adapter<BlogHolder>() {
    private var data = arrayListOf<Blog>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_blog, parent, false)
        return BlogHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: BlogHolder, position: Int) {
        holder.bind(data[position])
    }

    fun setData(blogs: List<Blog>) {
        data.clear()
        data.addAll(blogs)
        notifyDataSetChanged()
    }

}

internal class BlogHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {

    fun bind(model: Blog) {
        with(view) {
            blog_title.text = HtmlCompat.fromHtml(model.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
            blog_description.text = HtmlCompat.fromHtml(model.description, HtmlCompat.FROM_HTML_MODE_COMPACT)
            blog_owner.text = model.bloggerName
            blog_postdate.text = model.postdate

            setOnClickListener {
                val context = view.context
                Intent(context, WebViewActivity::class.java).apply {
                    putExtra(WebViewActivity.EXTRA_URL, model.link)
                }.run {
                    context.startActivity(this)
                }
            }
        }
    }
}