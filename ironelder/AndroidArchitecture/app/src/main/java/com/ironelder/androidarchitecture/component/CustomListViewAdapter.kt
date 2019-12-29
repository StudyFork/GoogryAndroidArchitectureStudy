package com.ironelder.androidarchitecture.component

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
//        val customItemView =
//            CustomItemViewImpl(parent.context)
//        val customItemViewHolder = CustomItemViewHolder(customItemView)
//        customItemView.setOnClickListener {
//            parent.context.startActivity(
//                Intent(
//                    Intent.ACTION_VIEW,
//                    Uri.parse(mItemList[customItemViewHolder.adapterPosition].link)
//                )
//            )
//        }
//        return customItemViewHolder
        val binding:ItemCustomItemViewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_custom_item_view,
            parent,
            false
        )

//        val customItemViewHolder = CustomItemViewHolder(customItemView)
//        customItemView.setOnClickListener {
//            parent.context.startActivity(
//                Intent(
//                    Intent.ACTION_VIEW,
//                    Uri.parse(mItemList[customItemViewHolder.adapterPosition].link)
//                )
//            )
//        }
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

    inner class CustomItemViewHolder(private val binding: ItemCustomItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item:ResultItem){
            binding.item = item
        }
    }

}