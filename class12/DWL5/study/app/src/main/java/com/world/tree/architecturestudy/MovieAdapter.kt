package com.world.tree.architecturestudy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter() : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private val movies : ArrayList<Movie.Item> = ArrayList()
    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val v : View = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(v)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        Glide.with(holder.itemView.imgPoster).load(movies[position].image)
            .centerCrop()
            .into(holder.itemView.imgPoster)
        holder.itemView.txtTitle.text = movies[position].title
        holder.itemView.txtDirector.text = movies[position].director
        holder.itemView.txtActor.text = movies[position].actor
    }

    fun addData(data: List<Movie.Item>) {
        movies.addAll(data)
        notifyDataSetChanged()
    }

    fun clearData() {
        movies.clear();
    }
}