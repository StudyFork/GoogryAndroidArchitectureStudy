package com.deepco.studyfork

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.deepco.studyfork.data.model.Item
import com.deepco.studyfork.databinding.MovieItemBinding

class RecyclerAdapterMovie :
    RecyclerView.Adapter<MyViewHolder>() {

    private var modelList = ArrayList<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            DataBindingUtil.inflate<MovieItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.movie_item,
                parent,
                false
            )
        return MyViewHolder(binding)
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

    fun clear() {
        this.modelList.clear()
        notifyDataSetChanged()
    }
}