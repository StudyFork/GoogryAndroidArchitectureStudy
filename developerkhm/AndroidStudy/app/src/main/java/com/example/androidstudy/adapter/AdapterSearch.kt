package com.ironelder.androidarchitecture.view

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidstudy.R
import com.example.androidstudy.databinding.ItemBlogViewBinding
import com.example.androidstudy.model.data.Item
import kotlinx.android.synthetic.main.item_blog_view.view.*



class AdapterSearch(
    private val context: Context?,
    private var itemList: ArrayList<Item>,
    private val type: String
) : RecyclerView.Adapter<AdapterSearch.CustomItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomItemViewHolder {
        val binding : ItemBlogViewBinding =  DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_blog_view, parent, false)
        return CustomItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomItemViewHolder, position: Int) {
        holder.binding.item = itemList.get(position)
    }

    override fun getItemCount() = itemList.size

    fun setItemList(list: ArrayList<Item>) {
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }

    inner class CustomItemViewHolder: RecyclerView.ViewHolder{
        lateinit var binding : ItemBlogViewBinding
        constructor(binding: ItemBlogViewBinding) : super(binding.root){
            this.binding = binding
        }
    }
}