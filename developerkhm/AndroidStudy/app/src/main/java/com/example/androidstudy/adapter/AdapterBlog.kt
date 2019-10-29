package com.ironelder.androidarchitecture.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.androidstudy.R
import com.example.androidstudy.api.data.Item
import kotlinx.android.synthetic.main.item_blog_view.view.*


class AdapterBlog(
    private val context: Context?,
    private var itemList: ArrayList<Item>,
    private val type: String
) :
    RecyclerView.Adapter<AdapterBlog.CustomItemViewHolder>() {


    class ItemViewBlog : ConstraintLayout {
        private val mItemType: String

        constructor(context: Context?, type: String) : super(context) {
            mItemType = type
            when (mItemType) {
                "blog", "news" -> itemImage.visibility = View.GONE
            }
        }

        init {
            LayoutInflater.from(context).inflate(R.layout.item_blog_view, this, true)

        }

        fun setData(item: Item) {
            itemTitle.text = HtmlCompat.fromHtml(item.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
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

    fun setItemList(list: ArrayList<Item>?) {
        itemList = list ?: arrayListOf()
        notifyDataSetChanged()
    }

    open fun addItemList(list: ArrayList<Item>?) {
        itemList.addAll(list ?: arrayListOf())
        notifyItemRangeInserted(itemList.size, list?.size ?: 0)
    }

    override fun onBindViewHolder(holder: CustomItemViewHolder, position: Int) {
        holder.setData(itemList[position])
    }

    inner class CustomItemViewHolder : RecyclerView.ViewHolder {
        private val mCustomItemView: ItemViewBlog

        constructor(v: View) : super(v) {
            mCustomItemView = v as ItemViewBlog
        }

        fun setData(item: Item) {
            mCustomItemView.setData(item)
        }
    }
}