package com.egiwon.architecturestudy.tabs

import android.content.Intent
import android.net.Uri
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
import com.egiwon.architecturestudy.ext.fromHtml
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

            tvTitle.text =
                list[position].title.fromHtml()

            tvDescription.text =
                (list[position].actor + list[position].description)
                    .fromHtml()


            linkUrl = list[position].link

            when (type) {
                "blog", "news" -> holder.imageThumbnail.visibility = View.GONE
                "movie", "book" -> {
                    holder.imageThumbnail.visibility = View.VISIBLE

                    Glide.with(holder.itemView.context)
                        .load(list[position].image)
                        .apply(RequestOptions.placeholderOf(R.mipmap.ic_launcher))
                        .into(holder.imageThumbnail)
                }
                else -> throw IllegalArgumentException()
            }

        }
    }

    fun setList(items: List<Content.Item>) {
        list.clear()
        items.iterator().forEach {
            list.add(it)
        }
        notifyDataSetChanged()
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