package com.ironelder.androidarchitecture.component

import android.content.Intent
import android.net.Uri
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ironelder.androidarchitecture.data.ResultItem
import com.ironelder.androidarchitecture.ui.CustomItemViewImpl


class CustomListViewAdapter(
    private val mType: String
) :
    RecyclerView.Adapter<CustomListViewAdapter.CustomItemViewHolder>() {
    private val mItemList: ArrayList<ResultItem> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomItemViewHolder {
        val customItemView =
            CustomItemViewImpl(parent.context)
        val customItemViewHolder = CustomItemViewHolder(customItemView)
        customItemView.setOnClickListener {
            parent.context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(mItemList[customItemViewHolder.adapterPosition].link)
                )
            )
        }
        return customItemViewHolder
    }

    override fun getItemCount() = mItemList.size

    fun setItemList(list: ArrayList<ResultItem>?) {
        mItemList.clear()
        if (!list.isNullOrEmpty()) {
            mItemList.addAll(list)
        }
        notifyDataSetChanged()
    }

    fun clearItemList() {
        mItemList.clear()
        notifyDataSetChanged()
    }

    fun addItemList(list: ArrayList<ResultItem>?) {
        mItemList.addAll(list ?: arrayListOf())
        notifyItemRangeInserted(mItemList.size - 1, list?.size ?: 0)
    }

    override fun onBindViewHolder(holder: CustomItemViewHolder, position: Int) {
        holder.setData(mItemList[position])
    }

    inner class CustomItemViewHolder(v: CustomItemViewImpl) : RecyclerView.ViewHolder(v) {
        private val mCustomItemViewImpl: CustomItemViewImpl by lazy { v }

        fun setData(item: ResultItem) {
            mCustomItemViewImpl.setData(item)
        }

    }
}