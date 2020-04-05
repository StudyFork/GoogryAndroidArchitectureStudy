package com.olaf.nukeolaf

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MovieAdpater(
    private var movies: ArrayList<MovieItem>
) : RecyclerView.Adapter<MovieAdpater.ViewHolder>() {

    fun setMovies(list: ArrayList<MovieItem>) {
        this.movies = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdpater.ViewHolder {
        val inflatedView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_movies_rv, parent)
        return (ViewHolder(inflatedView))
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieAdpater.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind() {

        }

    }
}