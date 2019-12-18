package com.example.studyapplication.main.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.studyapplication.R
import com.example.studyapplication.data.model.SearchMovieResult

class MovieAdapter : RecyclerView.Adapter<MovieHolder>() {
    private val arrMovieInfo : MutableList<SearchMovieResult.MovieInfo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view : View = inflater.inflate(R.layout.item_movie, parent, false)

        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        arrMovieInfo[position].let {
            with(holder) {
                tvTitle.text = it.title
                tvDirector.text = it.director
                tvActor.text = it.actor

                Glide.with(holder.itemView).load(it.image)
                    .apply(RequestOptions.centerCropTransform())
                    .into(ivImage)
            }
        }
    }

    override fun getItemCount(): Int {
        return arrMovieInfo.size
    }

    fun resetItem(items: Array<SearchMovieResult.MovieInfo>) {
        arrMovieInfo.clear()
        arrMovieInfo.addAll(items)
        notifyDataSetChanged()
    }
}