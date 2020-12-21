package com.deepco.studyfork

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.deepco.data.data.model.Item
import com.deepco.studyfork.databinding.MovieItemBinding

class MovieRecyclerAdapter :
    RecyclerView.Adapter<MovieViewHolder>() {

    private var modelList = ArrayList<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            DataBindingUtil.inflate<MovieItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.movie_item,
                parent,
                false
            )
        return MovieViewHolder(binding)
    }

    override fun getItemCount() = modelList.count()


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
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