package com.example.studyapplication.main.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.studyapplication.R
import com.example.studyapplication.data.model.MovieList
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(var arrMovieInfo : Array<MovieList.MovieInfo>) : RecyclerView.Adapter<MovieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view : View = inflater.inflate(R.layout.item_movie, parent, false)

        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.itemView.tvTitle.text = arrMovieInfo[position].title
        holder.itemView.tvDirector.text = arrMovieInfo[position].director
        holder.itemView.tvActor.text = arrMovieInfo[position].actor

        Glide.with(holder.itemView).load(arrMovieInfo[position].image)
            .apply(RequestOptions.centerCropTransform())
            .into(holder.itemView.ivImage)
    }

    override fun getItemCount(): Int {
        return arrMovieInfo.size
    }
}