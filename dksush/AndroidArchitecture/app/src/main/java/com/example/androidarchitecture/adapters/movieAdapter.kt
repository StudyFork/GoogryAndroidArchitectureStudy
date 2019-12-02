package com.example.androidarchitecture.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidarchitecture.R
import com.example.androidarchitecture.models.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class movieAdapter(
    val items: ArrayList<Movie>,
    val mContext: Context,
    val mOnItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<movieAdapter.movieHolder>() {


    interface OnItemClickListener {
        fun onItemClick(link: String)

    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): movieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return movieHolder(view)
    }


    override fun onBindViewHolder(holder: movieHolder, position: Int) {
        val movie = items.get(position)
        holder.itemView.movie_title.setText(movie.title)
        holder.itemView.director.setText(movie.director)
        holder.itemView.actors.setText(movie.actor)
        holder.itemView.pubDate.setText(movie.pubDate)
        holder.itemView.rating_star.rating = movie.userRating

        if (movie.image != null) {
            Glide.with(mContext)
                .load(movie.image)
                .into(holder.itemView.movie_image)
        }

        holder.itemView.setOnClickListener() {
            mOnItemClickListener?.let {
                it.onItemClick(movie.link)
            }
        }
    }


    inner class movieHolder(view: View) : RecyclerView.ViewHolder(view) {
    }

}