package com.example.architecturestudy.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.architecturestudy.R
import com.example.architecturestudy.data.model.MovieData
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movieList = ArrayList<MovieData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindData(movieList[position])
    }

    fun setData(movieList: List<MovieData>) {
        this.movieList.clear()
        this.movieList.addAll(movieList)
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val thumbnailImageView = itemView.thumbnailImageView
        private val titleTextView = itemView.titleTextView
        private val actorTextView = itemView.actorTextView

        fun bindData(movieData: MovieData) {
            movieData.image.let {
                Glide.with(itemView.context).load(movieData.image).into(thumbnailImageView)
            }
            titleTextView.text = movieData.title.replace("<b>", "").replace("</b>", "")
            actorTextView.text = movieData.actor
        }
    }
}