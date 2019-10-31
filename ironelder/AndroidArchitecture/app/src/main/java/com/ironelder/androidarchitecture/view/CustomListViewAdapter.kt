package com.ironelder.androidarchitecture.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ironelder.androidarchitecture.data.Item


class CustomListViewAdapter(
    private val mContext: Context?,
    private var mItemList: ArrayList<Item>,
    private val mType: String
) :
    RecyclerView.Adapter<CustomListViewAdapter.CustomItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomItemViewHolder {
        return CustomItemViewHolder(CustomItemView(mContext, mType))
    }

    override fun getItemCount() = mItemList.size

    fun setItemList(list: ArrayList<Item>?) {
        mItemList.clear()
        mItemList.addAll(list ?: arrayListOf())
        notifyDataSetChanged()
    }

    fun addItemList(list: ArrayList<Item>?) {
        mItemList.addAll(list ?: arrayListOf())
        notifyItemRangeInserted(mItemList.size, list?.size ?: 0)
    }

    override fun onBindViewHolder(holder: CustomItemViewHolder, position: Int) {
        holder.setData(mItemList[position])
    }

    inner class CustomItemViewHolder : RecyclerView.ViewHolder {
        private val mCustomItemView: CustomItemView

        constructor(v: View) : super(v) {
            mCustomItemView = v as CustomItemView
        }

        fun setData(item: Item) {
            mCustomItemView.setData(item)
            mCustomItemView.setOnClickListener {
                mContext?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.link)))
            }
        }

    }
}