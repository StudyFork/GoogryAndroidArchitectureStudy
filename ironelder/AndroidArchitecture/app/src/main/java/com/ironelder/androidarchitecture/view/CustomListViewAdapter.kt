package com.ironelder.androidarchitecture.view

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ironelder.androidarchitecture.data.Item

class CustomListViewAdapter(private val context: Context?, private var itemList: ArrayList<Item>) : RecyclerView.Adapter<CustomListViewAdapter.CustomItemViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomItemViewHolder {
        var view:View = CustomItemView(context)
        view.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
        return CustomItemViewHolder(view)
    }

    override fun getItemCount() = itemList?.size?:0

    fun setItemList(list:ArrayList<Item>?){
        itemList = list?: arrayListOf()
    }

    open fun addItemList(list:ArrayList<Item>?){
        itemList.addAll(list?: arrayListOf())
    }

    override fun onBindViewHolder(holder: CustomItemViewHolder, position: Int) {
    }

    inner class CustomItemViewHolder : RecyclerView.ViewHolder {
        private val mCustomItemView: CustomItemView

        constructor(v: View):super(v) {
            mCustomItemView = v as CustomItemView
        }
    }

}