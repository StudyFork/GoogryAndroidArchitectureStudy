package com.hyper.hyapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hyper.hyapplication.model.ResultGetSearchMovie

class MovieAdapter :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var movieList: ArrayList<ResultGetSearchMovie.Item> = ArrayList()

    override fun getItemCount(): Int = movieList.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val movieList = movieList[position]

        Glide.with(holder.itemView.context).load(movieList.image).override(1024)
            .into(holder.movieImage)
        holder.movieTitleTextView.text = movieList.title
        holder.movieDirectorTextView.text = movieList.director
        holder.movieActorTextView.text = movieList.actor
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieImage: ImageView = itemView.findViewById<ImageView>(R.id.movieImg)
        val movieTitleTextView: TextView = itemView.findViewById<TextView>(R.id.movieTitleTv)
        val movieDirectorTextView: TextView = itemView.findViewById<TextView>(R.id.movieDirectorTv)
        val movieActorTextView: TextView = itemView.findViewById<TextView>(R.id.movieActorTv)
    }

    fun resetData(item: List<ResultGetSearchMovie.Item>) {
        movieList.clear()
        movieList.addAll(item)
        notifyDataSetChanged()
    }
}
