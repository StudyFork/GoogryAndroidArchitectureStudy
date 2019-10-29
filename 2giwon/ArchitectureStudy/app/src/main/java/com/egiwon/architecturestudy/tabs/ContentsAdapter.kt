package com.egiwon.architecturestudy.tabs

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.egiwon.architecturestudy.R
import com.egiwon.architecturestudy.data.Content
import kotlinx.android.synthetic.main.rv_contents_item.view.*


class ContentsAdapter : RecyclerView.Adapter<ContentsAdapter.ViewHolder>() {

    private val list = ArrayList<Content.Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent)

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    inner class ViewHolder(
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
    ) {
        val tvTitle: TextView = itemView.tv_title
        val tvDescription: TextView = itemView.tv_description
        var linkUrl: String? = null
        val imageThumbnail: ImageView = itemView.iv_thumbnail
    }

    companion object {
        private const val layoutRes = R.layout.rv_contents_item
    }
}