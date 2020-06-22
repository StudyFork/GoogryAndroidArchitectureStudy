package com.sangjin.newproject.activity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sangjin.newproject.R
import com.sangjin.newproject.data.model.Movie
import com.sangjin.newproject.databinding.ItemMovieBinding

class MovieListAdapter(clickListener : ((Int) -> Unit)) : RecyclerView.Adapter<MovieListViewHolder>() {

    private val movieList = ArrayList<Movie>()

    private val onItemClickListener = clickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {

        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)

        val binding: ItemMovieBinding = ItemMovieBinding.bind(item)

        return MovieListViewHolder(binding).apply {
            itemView.setOnClickListener {
                onItemClickListener(adapterPosition)
            }
        }
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val movie = movieList.get(position)
        holder.bind(movie)
    }

    override fun getItemCount() = movieList.size


    fun refreshList(movieListFromActivity : List<Movie>){
        if(movieList.isNotEmpty()){
            movieList.clear()
        }

        movieList.addAll(movieListFromActivity)
        notifyDataSetChanged()
    }


    fun getMovieList(): ArrayList<Movie> = movieList


}