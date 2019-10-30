package com.egiwon.architecturestudy.tabs

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.egiwon.architecturestudy.R
import com.egiwon.architecturestudy.data.Content
import kotlinx.android.synthetic.main.rv_contents_item.view.*


class ContentsAdapter(
    private val type: String
) : RecyclerView.Adapter<ContentsAdapter.ViewHolder>() {

    private val list = ArrayList<Content.Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent)

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tvTitle.text =
                    Html.fromHtml(list[position].title, Html.FROM_HTML_MODE_COMPACT)
            } else {
                tvTitle.text = Html.fromHtml(list[position].title)
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tvDescription.text =
                    Html.fromHtml(list[position].description, Html.FROM_HTML_MODE_COMPACT)
            } else {
                tvDescription.text = Html.fromHtml(list[position].description)
            }

            linkUrl = list[position].link

            when (type) {
                "blog", "news" -> holder.imageThumbnail.visibility = View.GONE
                "movie" -> {
                    holder.imageThumbnail.visibility = View.VISIBLE

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        tvDescription.text =
                            Html.fromHtml(list[position].actor, Html.FROM_HTML_MODE_COMPACT)
                    } else {
                        tvDescription.text = Html.fromHtml(list[position].actor)
                    }
                }
                "book" -> holder.imageThumbnail.visibility = View.VISIBLE
                else -> throw IllegalArgumentException()
            }

            Glide.with(holder.itemView.context)
                .load(list[position].image)
                .apply(RequestOptions.placeholderOf(R.mipmap.ic_launcher))
                .into(holder.imageThumbnail)
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

        init {
            itemView.setOnClickListener {

                startActivity(
                    itemView.context,
                    Intent(
                        Intent.ACTION_VIEW, Uri.parse(linkUrl)
                    ),
                    null
                )
            }
        }
    }

    companion object {
        private const val layoutRes = R.layout.rv_contents_item
    }
}