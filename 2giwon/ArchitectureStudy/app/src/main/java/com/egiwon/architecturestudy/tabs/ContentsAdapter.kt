package com.egiwon.architecturestudy.tabs

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.egiwon.architecturestudy.Tab
import com.egiwon.architecturestudy.data.Content


class ContentsAdapter(private val tab: Tab) : RecyclerView.Adapter<ContentViewHolder>() {

    private val list = ArrayList<Content.Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder =
        when (Tab.values()[viewType]) {
            Tab.BLOG, Tab.NEWS -> ContentViewHolder(parent)
            Tab.MOVIE, Tab.BOOK -> ImageContentViewHolder(parent)
        }


    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holderContent: ContentViewHolder, position: Int) =
        holderContent.bind(list[position])


    fun setList(items: List<Content.Item>) {
        with(list) {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int = tab.ordinal
}