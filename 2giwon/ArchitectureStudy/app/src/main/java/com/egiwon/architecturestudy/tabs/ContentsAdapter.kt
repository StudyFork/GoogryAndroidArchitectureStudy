package com.egiwon.architecturestudy.tabs

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
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
        with(holder) {

            list[position].title?.let {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    tvTitle.text = Html.fromHtml(it, Html.FROM_HTML_MODE_COMPACT)
                } else {
                    tvTitle.text = Html.fromHtml(it)
                }
            }

            list[position].description?.let {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    tvDescription.text = Html.fromHtml(it, Html.FROM_HTML_MODE_COMPACT)
                } else {
                    tvDescription.text = Html.fromHtml(it)
                }
            }

            linkUrl = list[position].link?.let { it }

        }

        holder.itemView.setOnClickListener {

            startActivity(
                holder.itemView.context,
                Intent(
                    Intent.ACTION_VIEW, Uri.parse(holder.linkUrl)
                ),
                null
            )
        }
    }

    fun setList(items: List<Content.Item>) {
        items.iterator().forEach {
            list.add(it)
        }
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