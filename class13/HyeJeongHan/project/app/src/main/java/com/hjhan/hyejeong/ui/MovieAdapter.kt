package com.hjhan.hyejeong.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hjhan.hyejeong.R
import com.hjhan.hyejeong.data.model.Item
import com.hjhan.hyejeong.databinding.ItemMovieBinding

class MovieAdapter :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var list = mutableListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = DataBindingUtil.inflate<ItemMovieBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_movie,
            parent,
            false
        )

        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        holder.bind(list[position])

    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setMovieList(list: List<Item>) {
        this.list = list.toMutableList()

        notifyDataSetChanged()
    }

    class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Item) {

            binding.titleTextView.text = data.title
            binding.subTitleTextView.text = data.subtitle
            binding.directorTextView.text = data.director

            Glide.with(binding.movieImageImageView.context)
                .load(data.image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.movieImageImageView)


        }
    }
}