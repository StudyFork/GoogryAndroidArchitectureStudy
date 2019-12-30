package com.ironelder.androidarchitecture.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidstudy.R
import com.example.androidstudy.model.data.Item
import kotlinx.android.synthetic.main.item_blog_view.view.*


class AdapterSearch(
    private val context: Context?,
    private var itemList: ArrayList<Item>,
    private val type: String
) :
    RecyclerView.Adapter<AdapterSearch.CustomItemViewHolder>() {


    class ItemViewBlog : ConstraintLayout {
        val itemType: String

        constructor(context: Context?, type: String) : super(context) {
            itemType = type
        }

        init {
            LayoutInflater.from(context).inflate(R.layout.item_blog_view, this, true)

        }

        fun setData(item: Item) {
            itemTitle.text = HtmlCompat.fromHtml(item.title, HtmlCompat.FROM_HTML_MODE_COMPACT)

            itemContent.text = HtmlCompat.fromHtml(
                item.description,
                HtmlCompat.FROM_HTML_MODE_COMPACT
            )


            item.image.let {
                Glide
                    .with(context)
                    .load(it)
                    .centerCrop()
                    .into(itemImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomItemViewHolder {
        var view: View = ItemViewBlog(context, type)
        view.layoutParams = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
        return CustomItemViewHolder(view)
    }

    override fun getItemCount() = itemList.size

    fun setItemList(list: ArrayList<Item>) {
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CustomItemViewHolder, position: Int) {
        holder.setData(itemList[position])
    }

    inner class CustomItemViewHolder : RecyclerView.ViewHolder {
        private val customItemView: ItemViewBlog

        constructor(v: View) : super(v) {
            customItemView = v as ItemViewBlog
        }

        fun setData(item: Item) {
            customItemView.setData(item)
        }
    }
}