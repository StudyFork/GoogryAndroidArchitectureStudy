package com.god.taeiim.myapplication.ui

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.god.taeiim.myapplication.R
import com.god.taeiim.myapplication.Tabs
import com.god.taeiim.myapplication.api.model.SearchResult
import com.god.taeiim.myapplication.extensions.fromHtml
import com.god.taeiim.myapplication.getSearchType
import kotlinx.android.synthetic.main.item_contents.view.*

class SearchResultRecyclerAdapter(private val searchType: String) :
    RecyclerView.Adapter<SearchResultRecyclerAdapter.ViewHolder>() {
    private val resultList = mutableListOf<SearchResult.Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_contents,
                parent,
                false
            )
        )

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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        resultList[position].let { item ->
            with(holder.itemView) {
                titleTv.text = item.title.fromHtml()
                descTv.text = item.description.fromHtml()

                when (searchType) {
                    getSearchType(Tabs.BLOG) -> {
                        subTitleTv.text = item.postdate.fromHtml()
                    }
                    getSearchType(Tabs.NEWS) -> {
                        subTitleTv.visibility = View.GONE
                    }
                    getSearchType(Tabs.MOVIE) -> {
                        setImage(item, holder)
                        subTitleTv.text = item.pubDate.fromHtml()
                        descTv.text = (item.director + item.actor).fromHtml()
                    }
                    getSearchType(Tabs.BOOK) -> {
                        setImage(item, holder)
                        subTitleTv.text = item.author.fromHtml()
                    }
                }
            }
            holder.link = item.link
        }
    }

    private fun View.setImage(
        item: SearchResult.Item,
        holder: ViewHolder
    ) {
        with(item.image) {
            if (!this.isNullOrBlank()) {
                thumbnailIv.visibility = View.VISIBLE
                Glide.with(holder.itemView.context)
                    .load(this)
                    .into(thumbnailIv)

            } else {
                thumbnailIv.visibility = View.GONE
            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var link: String? = ""

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

