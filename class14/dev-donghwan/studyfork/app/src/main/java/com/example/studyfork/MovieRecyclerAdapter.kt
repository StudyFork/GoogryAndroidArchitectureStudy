package com.example.studyfork

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.studyfork.databinding.ItemMovieBinding
import com.example.studyfork.data.model.MovieSearchResponse

class MovieRecyclerAdapter : RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder>() {
    private val items: ArrayList<MovieSearchResponse.MovieItem> = ArrayList()

    fun itemChange(item: List<MovieSearchResponse.MovieItem>) {
        this.items.clear()
        this.items.addAll(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie,
            parent,
            false
        ))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun onBind(item: MovieSearchResponse.MovieItem) {
            binding.tvTitle.text = item.title
            Glide.with(itemView).load(item.image).into(binding.ivImage)
        }
    }
}