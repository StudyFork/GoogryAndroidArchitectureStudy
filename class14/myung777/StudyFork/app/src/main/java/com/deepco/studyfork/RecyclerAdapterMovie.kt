package com.deepco.studyfork

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapterMovie :
    RecyclerView.Adapter<MyViewHolder>() {

    private var modelList = ArrayList<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return this.modelList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(this.modelList[position])
    }

    fun setItemList(modelList: ArrayList<Item>) {
        this.modelList.clear()
        this.modelList = modelList
        notifyDataSetChanged()
    }
}