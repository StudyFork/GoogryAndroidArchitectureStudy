package com.practice.achitecture.myproject

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.practice.achitecture.myproject.model.SearchedItem
import kotlinx.android.synthetic.main.item_blog_and_news.view.*

class SearchBlogAndNewsAdapter(
    private var items: List<SearchedItem>,
    private val clickListener: (SearchedItem) -> Unit
) :
    RecyclerView.Adapter<SearchBlogAndNewsAdapter.ViewHolder>() {

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.run {
            bind(item, clickListener)
            itemView.tag = item
        }
    }

    fun notifyDataSetChanged(newItems: List<SearchedItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_blog_and_news, parent, false)
        return ViewHolder(inflatedView)
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        fun bind(item: SearchedItem?, clickListener: (SearchedItem) -> Unit) {
            val title = item?.title ?: ""
            val description = item?.description ?: ""

            itemView.setBackgroundColor(Color.WHITE)
            itemView.tv_title.text =
                HtmlCompat.fromHtml(title, HtmlCompat.FROM_HTML_MODE_COMPACT)
            itemView.tv_description.text =
                HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT)
            itemView.setOnClickListener {
                if (item != null) {
                    clickListener(item)
                }
            }
        }
    }
}