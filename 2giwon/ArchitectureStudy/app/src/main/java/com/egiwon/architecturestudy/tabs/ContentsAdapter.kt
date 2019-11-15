package com.egiwon.architecturestudy.tabs

import android.content.Intent
import android.net.Uri
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.egiwon.architecturestudy.R
import com.egiwon.architecturestudy.Tab
import com.egiwon.architecturestudy.base.BaseViewHolder
import com.egiwon.architecturestudy.data.Content
import com.egiwon.architecturestudy.ext.fromHtml
import com.egiwon.architecturestudy.ext.loadAsync


class ContentsAdapter(
    private val tab: Tab
) : RecyclerView.Adapter<BaseViewHolder>() {

    private val list = ArrayList<Content.Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        when (viewType) {
            Tab.BLOG.ordinal, Tab.NEWS.ordinal -> TextViewHolder(parent)
            Tab.MOVIE.ordinal, Tab.BOOK.ordinal -> ImageViewHolder(parent)
            else -> throw IllegalArgumentException()
        }.also { viewHolder ->
            viewHolder.itemView.setOnClickListener {
                viewHolder.linkUrl?.let { url ->
                    startActivity(
                        it.context,
                        Intent(
                            Intent.ACTION_VIEW, Uri.parse(url)
                        ),
                        null
                    )
                }
            }
        }


    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        with(holder) {

            tvTitle.text =
                list[position].title.fromHtml()

            tvDescription.text =
                (list[position].actor + list[position].description)
                    .fromHtml()


            linkUrl = list[position].link

            (holder as? ImageViewHolder)?.run {
                loadImage(holder, position)
            }
        }
    }

    private fun loadImage(
        holder: ImageViewHolder,
        position: Int
    ) {
        holder.imageThumbnail.loadAsync(
            list[position].image,
            RequestOptions.placeholderOf(R.mipmap.ic_launcher)
        )
    }

    fun setList(items: List<Content.Item>) {
        with(list) {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int = tab.ordinal
}