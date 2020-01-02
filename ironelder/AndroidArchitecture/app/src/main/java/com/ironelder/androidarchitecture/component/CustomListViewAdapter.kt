package com.ironelder.androidarchitecture.component

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ironelder.androidarchitecture.R
import com.ironelder.androidarchitecture.data.ResultItem
import com.ironelder.androidarchitecture.databinding.ItemCustomItemViewBinding


class CustomListViewAdapter :
    RecyclerView.Adapter<CustomListViewAdapter.CustomItemViewHolder>() {
    private val mItemList: ArrayList<ResultItem> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomItemViewHolder {
        val binding: ItemCustomItemViewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_custom_item_view,
            parent,
            false
        )

        return CustomItemViewHolder(binding)
    }

    override fun getItemCount() = mItemList.size

    fun setItemList(list: ArrayList<ResultItem>?) {
        mItemList.clear()
        if (!list.isNullOrEmpty()) {
            mItemList.addAll(list)
        }
        notifyDataSetChanged()
    }

    fun addItemList(list: ArrayList<ResultItem>?) {
        mItemList.addAll(list ?: arrayListOf())
        notifyItemRangeInserted(mItemList.size - 1, list?.size ?: 0)
    }

    override fun onBindViewHolder(holder: CustomItemViewHolder, position: Int) {
        holder.bind(mItemList[position])
    }

    class CustomItemViewHolder(private val binding: ItemCustomItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResultItem) {
            binding.item = item
            binding.root.setOnClickListener {
                binding.root.context.startActivity(
                    Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
                )
            }
        }

    }

}