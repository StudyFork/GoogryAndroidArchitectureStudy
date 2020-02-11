package com.example.myapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.model.MovieResult
import com.example.myapplication.databinding.ItemMovieBinding

class MovieRecyclerViewAdpater(val itemClick: (movieDetail: String) -> Unit) :
    RecyclerView.Adapter<MovieRecyclerViewAdpater.MovieViewHolder>() {

    private val results = mutableListOf<MovieResult.Item>()
    private lateinit var binding: ItemMovieBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie,
            parent,
            false
        )

        return MovieViewHolder(binding)
    }

    inner class MovieViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                itemClick(results[adapterPosition].link)
            }
            binding.executePendingBindings()
        }

        fun bind(movieItem: MovieResult.Item) {
            binding.item = movieItem
        }
    }

    override fun getItemCount() = results.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val element = results[position]
        holder.bind(element)
    }

    fun setItems(items: List<MovieResult.Item>) {
        results.clear()
        results.addAll(items)
        notifyDataSetChanged()
    }

}