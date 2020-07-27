package com.hyper.hyapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hyper.hyapplication.databinding.ItemMovieBinding
import com.hyper.hyapplication.model.ResultGetSearchMovie

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var movieList: ArrayList<ResultGetSearchMovie.Item> = ArrayList()

    override fun getItemCount(): Int = movieList.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemMovieBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movieList = movieList[position]
        holder.onBind(movieList)
    }

    class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResultGetSearchMovie.Item) {
            binding.movieData = data
            binding.executePendingBindings()
        }
    }

    fun resetData(item: List<ResultGetSearchMovie.Item>) {
        movieList.clear()
        movieList.addAll(item)
        notifyDataSetChanged()
    }
}
