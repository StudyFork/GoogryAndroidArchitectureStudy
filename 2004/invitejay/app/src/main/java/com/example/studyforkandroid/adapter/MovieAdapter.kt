package com.example.studyforkandroid.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.studyforkandroid.R
import com.example.studyforkandroid.data.Movie
import com.example.studyforkandroid.databinding.ItemMovieBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieVh>() {

    private val movieList = mutableListOf<Movie>()
    lateinit var binding: ItemMovieBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVh {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie,
            parent,
            false
        )
        val movieVh = MovieVh(binding)
        binding.root.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(movieList[movieVh.adapterPosition].link)
            parent.context.startActivity(intent)
        }
        return movieVh
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun replaceAll(movieList: List<Movie>) {
        clear()
        this.movieList.addAll(movieList)
        notifyDataSetChanged()
    }

    fun clear() {
        this.movieList.clear()
    }

    override fun onBindViewHolder(holder: MovieVh, position: Int) {
        holder.bind(movieList[position])
    }

    inner class MovieVh(binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) {
            binding.movie = item
            binding.executePendingBindings()
        }
    }
}