package com.example.dkarch

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dkarch.data.entity.Movie
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(
    private val movieList: ArrayList<Movie>,
    private val isClicked: (String) -> Unit
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movieList[position]
        holder.bind(item, isClicked)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
    ) {
        private val titleTextView: TextView = itemView.title
        private val subtitleTextView: TextView = itemView.tv_sub_title
        private val actorTextView: TextView = itemView.tv_actor
        private val ratingsTextView: TextView = itemView.tv_rating

        fun bind(item: Movie, isClicked: (String) -> Unit) {
            titleTextView.text = item.title
            subtitleTextView.text = item.subtitle
            actorTextView.text = item.actor
            ratingsTextView.text = item.userRating
        }
    }

}
