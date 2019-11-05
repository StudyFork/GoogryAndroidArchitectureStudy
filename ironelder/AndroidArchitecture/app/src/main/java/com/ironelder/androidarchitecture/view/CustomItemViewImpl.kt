package com.ironelder.androidarchitecture.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ironelder.androidarchitecture.R
import com.ironelder.androidarchitecture.common.BLOG
import com.ironelder.androidarchitecture.common.NEWS
import com.ironelder.androidarchitecture.data.Item
import kotlinx.android.synthetic.main.item_custom_item_view.view.*

class CustomItemViewImpl(context: Context?, private val mItemType: String) : ConstraintLayout(context),
    CustomItemView<Item> {

    init {
        LayoutInflater.from(context).inflate(R.layout.item_custom_item_view, this, true)
        layoutParams = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
    }

    override fun setData(item: Item) {
        tv_itemTitle.text = HtmlCompat.fromHtml(item.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
        tv_itemContent.text = HtmlCompat.fromHtml(
            item.description ?: item.director,
            HtmlCompat.FROM_HTML_MODE_COMPACT
        )
        when (mItemType) {
            BLOG, NEWS -> {
                iv_itemImage.visibility = View.GONE
            }
            else -> {
                iv_itemImage.visibility = View.VISIBLE
                Glide.with(context).load(item.image).centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background).into(iv_itemImage)
            }
        }
    }
}