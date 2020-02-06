package com.example.study.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.study.R
import com.example.study.data.model.Movie
import com.example.study.databinding.MovieItemBinding


class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private lateinit var binding:MovieItemBinding
    private val movies = mutableListOf<Movie>()
    private var itemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.movie_item, parent, false)
        val holder = MovieViewHolder(binding.root)
        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(movies[holder.adapterPosition])
        }

        return holder
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun setOnItemClickListener(listener: (Movie) -> Unit) {
        itemClickListener = object :
            ItemClickListener {
            override fun onItemClick(movie: Movie) {
                listener(movie)
            }
        }
    }

    fun setItem(item: List<Movie>) {
        movies.clear()
        movies.addAll(item)
        notifyDataSetChanged()
    }

    override fun getItemCount() = movies.size

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            binding.movie = movie
        }
    }

    interface ItemClickListener {
        fun onItemClick(movie: Movie)
    }
}


