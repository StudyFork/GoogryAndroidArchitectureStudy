package com.practice.achitecture.myproject

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.practice.achitecture.myproject.model.Item
import kotlinx.android.synthetic.main.item_blog_and_news.view.*

class SearchBlogAndNewsAdapter(private val items: ArrayList<Item>) :
    RecyclerView.Adapter<SearchBlogAndNewsAdapter.ViewHolder>() {

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val listener = View.OnClickListener { it ->
            val url = Uri.parse(item.link)
            val intent = Intent(Intent.ACTION_VIEW, url)
            it.context.startActivity(intent)
        }
        holder.run {
            bind(listener, item)
            itemView.tag = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val inflatedView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_blog_and_news, parent, false)

        return ViewHolder(inflatedView)
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View = v

        fun bind(listener: View.OnClickListener, item: Item?) {
            view.tv_title.text =
                HtmlCompat.fromHtml(item?.title.toString(), HtmlCompat.FROM_HTML_MODE_COMPACT)
            view.tv_description.text =
                HtmlCompat.fromHtml(item?.description.toString(), HtmlCompat.FROM_HTML_MODE_COMPACT)
            view.setOnClickListener(listener)
        }
    }
}