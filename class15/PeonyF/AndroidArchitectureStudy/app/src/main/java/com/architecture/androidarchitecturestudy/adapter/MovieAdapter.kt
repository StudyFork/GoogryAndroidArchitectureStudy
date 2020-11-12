package com.architecture.androidarchitecturestudy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.architecture.androidarchitecturestudy.R
import com.architecture.androidarchitecturestudy.data.model.Movie

class MovieAdapter :
    RecyclerView.Adapter<MovieViewHolder>() {

    private val modelList = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun getItemCount() = modelList.count()

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(this.modelList[position])
    }

    fun setItemList(items: List<Movie>) {
        this.modelList.clear()
        this.modelList.addAll(items)
        notifyDataSetChanged()
    }
}