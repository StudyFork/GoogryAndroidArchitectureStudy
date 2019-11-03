package com.practice.achitecture.myproject

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.practice.achitecture.myproject.model.SearchedItem
import common.GlideWrapper
import kotlinx.android.synthetic.main.item_blog_and_news.view.tv_description
import kotlinx.android.synthetic.main.item_blog_and_news.view.tv_title
import kotlinx.android.synthetic.main.item_book_and_movie.view.*

class SearchMovieAndBookAdapter : RecyclerView.Adapter<SearchMovieAndBookAdapter.ViewHolder>() {

    private var items: List<SearchedItem> = listOf()


    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.run {
            bind(item)
            itemView.tag = item
        }
    }

    fun notifyDataSetChanged(newItems: List<SearchedItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book_and_movie, parent, false)
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

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: SearchedItem?) {

            if (item?.image != null) {
                GlideWrapper.showImage(itemView.iv_main_image, item.image)
            }

            val title = item?.title ?: ""
            val content = item?.description ?: item?.director ?: ""

            itemView.tv_title.text =
                HtmlCompat.fromHtml(title, HtmlCompat.FROM_HTML_MODE_COMPACT)
            itemView.tv_description.text =
                HtmlCompat.fromHtml(content, HtmlCompat.FROM_HTML_MODE_COMPACT)
        }
    }
}