package com.deepco.studyfork

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deepco.studyfork.data.model.Item

class RecyclerAdapterMovie :
    RecyclerView.Adapter<MyViewHolder>() {

    private var modelList = ArrayList<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun getItemCount() = modelList.count()


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(this.modelList[position])
    }

    fun setItemList(modelList: List<Item>) {
        this.modelList.clear()
        this.modelList.addAll(modelList)
        notifyDataSetChanged()
    }
}