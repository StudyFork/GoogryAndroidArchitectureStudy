package com.example.architecturestudy.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.architecturestudy.R
import com.example.architecturestudy.data.model.MovieItem
import com.example.architecturestudy.databinding.ItemMovieBinding
import com.example.architecturestudy.ui.startWebView
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    private val movieItem : MutableList<MovieItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding = DataBindingUtil.inflate<ItemMovieBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_movie,
            parent,
            false
        )

        return MovieHolder(binding).apply {
            itemView.setOnClickListener { v -> v.startWebView(movieItem[adapterPosition].link) }
        }
    }

    override fun getItemCount(): Int {
        return movieItem.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(movieItem[position])
    }

    fun update(movieList : List<MovieItem>) {
        this.movieItem.clear()
        this.movieItem.addAll(movieList)
        notifyDataSetChanged()
    }

    class MovieHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item : MovieItem) {
            binding.movie = item
        }
    }
}