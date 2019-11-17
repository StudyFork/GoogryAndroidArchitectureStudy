package com.god.taeiim.myapplication.ui

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.god.taeiim.myapplication.R
import com.god.taeiim.myapplication.Tabs
import com.god.taeiim.myapplication.api.model.SearchResult
import com.god.taeiim.myapplication.extensions.fromHtml
import com.god.taeiim.myapplication.extensions.loadImage
import kotlinx.android.synthetic.main.item_contents.view.*

class SearchResultRecyclerAdapter(private val tab: Tabs) :
    RecyclerView.Adapter<SearchResultRecyclerAdapter.BaseViewHolder<*>>() {
    private val resultList = mutableListOf<SearchResult.Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contents, parent, false)

        return when (viewType) {
            Tabs.BLOG.ordinal -> BlogViewHolder(view)
            Tabs.NEWS.ordinal -> NewsViewHolder(view)
            Tabs.MOVIE.ordinal -> MovieViewHolder(view)
            Tabs.BOOK.ordinal -> BookViewHolder(view)
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int = resultList.size

    fun setItems(items: List<SearchResult.Item>) {
        resultList.clear()
        resultList.addAll(items)
        notifyDataSetChanged()
    }

    fun clearItems() {
        resultList.clear()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val element = resultList[position]
        when (holder) {
            is BlogViewHolder -> holder.bind(element)
            is NewsViewHolder -> holder.bind(element)
            is MovieViewHolder -> holder.bind(element)
            is BookViewHolder -> holder.bind(element)
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return tab.ordinal
    }

    inner class BlogViewHolder(itemView: View) : BaseViewHolder<SearchResult.Item>(itemView) {
        override fun bind(item: SearchResult.Item) {
            item.commonBind()
            itemView.subTitleTv.text = item.postdate.fromHtml()
        }
    }

    inner class NewsViewHolder(itemView: View) : BaseViewHolder<SearchResult.Item>(itemView) {
        override fun bind(item: SearchResult.Item) {
            item.commonBind()
            itemView.subTitleTv.visibility = View.GONE
        }
    }

    inner class MovieViewHolder(itemView: View) : BaseViewHolder<SearchResult.Item>(itemView) {
        override fun bind(item: SearchResult.Item) {
            itemView.setImage(item)
            with(itemView) {
                item.commonBind()
                subTitleTv.text = item.pubDate.fromHtml()
                descTv.text = (item.director + item.actor).fromHtml()
            }
        }
    }

    inner class BookViewHolder(itemView: View) : BaseViewHolder<SearchResult.Item>(itemView) {
        override fun bind(item: SearchResult.Item) {
            item.commonBind()
            itemView.setImage(item)
            itemView.subTitleTv.text = item.author.fromHtml()
        }
    }

    private fun View.setImage(
        item: SearchResult.Item
    ) {
        with(item.image) {
            if (!this.isNullOrBlank()) {
                thumbnailIv.visibility = View.VISIBLE
                thumbnailIv.loadImage(this)

            } else {
                thumbnailIv.visibility = View.GONE
            }
        }
    }

    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var link: String? = ""

        abstract fun bind(item: T)

        fun SearchResult.Item.commonBind() {
            with(itemView) {
                titleTv.text = title.fromHtml()
                descTv.text = description.fromHtml()
            }
            this@BaseViewHolder.link = link
        }

        init {
            itemView.setOnClickListener {
                startActivity(
                    itemView.context,
                    Intent(Intent.ACTION_VIEW, Uri.parse(link)),
                    null
                )
            }
        }
    }
}

