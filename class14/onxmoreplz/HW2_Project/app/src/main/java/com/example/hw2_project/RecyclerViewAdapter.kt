package com.example.hw2_project

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hw2_project.data.api.NaverMovieData
import com.example.hw2_project.databinding.MovieItemBinding

class RecyclerViewAdapter() : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){

    private val arrListOfMovie :ArrayList<NaverMovieData.NaverMovie> = ArrayList()

    fun updateMovieList(movies : List<NaverMovieData.NaverMovie>){
        this.arrListOfMovie.clear()
        this.arrListOfMovie.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<MovieItemBinding>(LayoutInflater.from(parent.context), R.layout.movie_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(arrListOfMovie.get(position))
    }

    override fun getItemCount(): Int {
        return arrListOfMovie.size
    }

    class ViewHolder(private val movieItemBinding: MovieItemBinding) : RecyclerView.ViewHolder(movieItemBinding.root){

        fun bindItem( movie : NaverMovieData.NaverMovie){
            movieItemBinding.movieItem = movie
            movieItemBinding.executePendingBindings()
        }
    }

}