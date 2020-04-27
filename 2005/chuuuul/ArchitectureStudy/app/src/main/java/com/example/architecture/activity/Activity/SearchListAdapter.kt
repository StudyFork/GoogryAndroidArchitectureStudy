package com.example.architecture.activity.Activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.architecture.R
import kotlinx.android.synthetic.main.item_movie.view.*
import java.util.*

class SearchListAdapter(private val movies: ArrayList<MovieVO>) : RecyclerView.Adapter<SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)

        return SearchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.count()
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.itemView.textView_movie_title.text = movies[position].title
        holder.itemView.textView_movie_pubDate.text = movies[position].pubDate

        holder.itemView.ratingBar_movie_rating.rating = movies[position].userRating
    }

}

class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {


}