package com.example.kyudong3.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kyudong3.data.model.Movie
import com.example.kyudong3.databinding.ItemMovieBinding
import com.example.kyudong3.databinding.ItemMovieBindingImpl

class SearchMovieRvAdapter :
    RecyclerView.Adapter<SearchMovieRvAdapter.SearchMovieVH>() {

    private val movieList = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieVH {
        val binding = ItemMovieBindingImpl.inflate(LayoutInflater.from(parent.context))
        return SearchMovieVH(binding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: SearchMovieVH, position: Int) {
        holder.bind(movieList[position])
    }

    fun setMovieList(movieList: List<Movie>) {
        this.movieList.apply {
            clear()
            addAll(movieList)
        }
    }

    inner class SearchMovieVH(val itemMovieBinding: ItemMovieBinding) :
        RecyclerView.ViewHolder(itemMovieBinding.root) {
        lateinit var movie: Movie

        init {
            itemMovieBinding.root.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(movie.link))
                it.context.startActivity(intent)
            }
        }

        fun bind(movie: Movie) {
            itemMovieBinding.movieItem = movie
            this.movie = movie
        }
    }
}