package com.god.taeiim.myapplication.ui

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.god.taeiim.myapplication.R
import com.god.taeiim.myapplication.api.model.SearchResult
import com.god.taeiim.myapplication.extensions.fromHtml
import kotlinx.android.synthetic.main.item_contents.view.*

class SearchResultRecyclerAdapter : RecyclerView.Adapter<SearchResultRecyclerAdapter.ViewHolder>() {
    private var resultList = ArrayList<SearchResult.Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_contents,
                parent,
                false
            )
        )

    override fun getItemCount(): Int {
        return resultList.size
    }

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
        resultList[position].let { blog ->
            with(holder.itemView) {
                titleTv.text = blog.title.fromHtml()
                subTitleTv.text = blog.postdate.fromHtml()
                descTv.text = blog.description.fromHtml()
            }
            holder.link = blog.link
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var link: String? = ""

        init {
            itemView.setOnClickListener {
                startActivity(
                    itemView.context,
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(link)
                    ),
                    null
                )
            }
        }
    }
}

