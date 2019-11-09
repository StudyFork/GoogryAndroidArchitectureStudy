package com.practice.achitecture.myproject

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.practice.achitecture.myproject.model.SearchedItem
import kotlinx.android.synthetic.main.item_blog_and_news.view.*

class SearchBlogAndNewsAdapter : RecyclerView.Adapter<SearchBlogAndNewsAdapter.ViewHolder>() {

    private var items: ArrayList<SearchedItem> = arrayListOf()

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.run {
            bind(item)
            itemView.tag = item
        }
    }

    fun notifyDataSetChanged(newItems: List<SearchedItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_blog_and_news, parent, false)
        val viewHolder = ViewHolder(inflatedView)
        inflatedView.setOnClickListener {
            parent.context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(items[viewHolder.adapterPosition].link)
                )
            )
        }

        return viewHolder
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        fun bind(item: SearchedItem?) {
            val title = item?.title ?: ""
            val description = item?.description ?: ""

            itemView.setBackgroundColor(Color.WHITE)
            itemView.tv_title.text =
                HtmlCompat.fromHtml(title, HtmlCompat.FROM_HTML_MODE_COMPACT)
            itemView.tv_description.text =
                HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT)
        }
    }
}