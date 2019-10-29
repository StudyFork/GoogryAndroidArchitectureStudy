package com.ironelder.androidarchitecture.view

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ironelder.androidarchitecture.data.Item

class AdapterBlog(
    private val context: Context?,
    private var itemList: ArrayList<Item>,
    private val type: String
) :
    RecyclerView.Adapter<AdapterBlog.CustomItemViewHolder>() {
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