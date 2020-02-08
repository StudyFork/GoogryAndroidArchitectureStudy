package com.studyfork.architecturestudy.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.studyfork.architecturestudy.data.model.MovieResponse
import com.studyfork.architecturestudy.databinding.ItemMovieBinding

class MovieResultRVAdapter(private val itemClick: (movieLink: String) -> Unit) :
    RecyclerView.Adapter<MovieResultRVAdapter.MovieResultVH>() {

    private val items = mutableListOf<MovieResponse.Item>()

    fun setItems(items: List<MovieResponse.Item>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieResultVH {
        val binding: ItemMovieBinding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        binding.root.setOnClickListener {
            binding.movie?.link?.let(itemClick)
        }

        return MovieResultVH(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MovieResultVH, position: Int) {
        holder.bind(this.items[position])
    }

    inner class MovieResultVH(@NonNull private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieResponse.Item) {
            binding.movie = item
        }
    }
}