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
        val searchMovieVH = SearchMovieVH(binding)

        binding.root.setOnClickListener {
            val intent =
                Intent(Intent.ACTION_VIEW, Uri.parse(movieList[searchMovieVH.adapterPosition].link))
            parent.context.startActivity(intent)
        }

        return searchMovieVH
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: SearchMovieVH, position: Int) {
        holder.bind(movieList[position])
    }

    fun setMovieList(movieList: List<Movie>?) {
        this.movieList.apply {
            clear()
            movieList?.let {
                addAll(it)
                notifyDataSetChanged()
            }
        }
    }

    inner class SearchMovieVH(val itemMovieBinding: ItemMovieBinding) :
        RecyclerView.ViewHolder(itemMovieBinding.root) {

        fun bind(movie: Movie) {
            itemMovieBinding.movieItem = movie
            itemMovieBinding.executePendingBindings()
        }
    }
}