package com.example.study

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.study.databinding.MovieViewBinding
import com.example.study.model.Movie


class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private val movies = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            MovieViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }


    fun setItem(item: List<Movie>) {
        movies.clear()
        movies.addAll(item)
        notifyDataSetChanged()
    }

    override fun getItemCount() = movies.size

    class MovieViewHolder(private val binding: MovieViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                var url = ""

            }
        }

        fun bind(movie: Movie) {
            with(binding) {
                Glide.with(this.root)
                    .load(movie.image)
                    .into(this.imageView)
                titleView.text = movie.title
                ratingBar.rating = movie.userRating.toFloat() / 2
                pubdateView.text = movie.pubDate
                directorView.text = movie.director
                actorView.text = movie.actor

            }
        }
    }

    interface OnItemClickListener {
        fun onItemClickListener(position: Int, view: View)
    }
}

