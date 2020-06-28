package com.world.tree.architecturestudy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(data : List<Movie.Item>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var movies : List<Movie.Item> = data
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
        holder.itemView.findViewById<TextView>(R.id.txtTitle).text = movies[position].title
        holder.itemView.findViewById<TextView>(R.id.txtDirector).text = movies[position].director
        holder.itemView.findViewById<TextView>(R.id.txtActor).text = movies[position].actor
    }

    fun setDataList(data: List<Movie.Item>) {
        this.movies = data
        notifyDataSetChanged()
    }
}