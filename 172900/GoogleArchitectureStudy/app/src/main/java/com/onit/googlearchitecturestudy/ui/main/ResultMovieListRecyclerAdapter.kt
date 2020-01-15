package com.onit.googlearchitecturestudy.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.onit.googlearchitecturestudy.Movie
import com.onit.googlearchitecturestudy.R
import com.onit.googlearchitecturestudy.databinding.HolderMovieBinding

class ResultMovieListRecyclerAdapter(
    private val listener: ClickMovieListener
) : RecyclerView.Adapter<ResultMovieListRecyclerAdapter.MovieViewHolder>() {

    private val list: ArrayList<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val binding = DataBindingUtil.inflate<HolderMovieBinding>(
            LayoutInflater.from(parent.context),
            R.layout.holder_movie,
            parent,
            false
        )

        return MovieViewHolder(binding, listener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = list[position]
        holder.bind(movie)
    }

    fun setMovieList(newMovieList: List<Movie>) {
        with(list) {
            clear()
            addAll(newMovieList)
        }
        notifyDataSetChanged()
    }

    fun getMovieURL(position: Int): String {
        return list[position].link
    }

    inner class MovieViewHolder(
        private val binding: HolderMovieBinding,
        clickListener: ClickMovieListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.onClick = { clickListener(adapterPosition) }
        }

        fun bind(movie: Movie) {
            binding.movie = movie
            binding.executePendingBindings()
        }
    }
}
