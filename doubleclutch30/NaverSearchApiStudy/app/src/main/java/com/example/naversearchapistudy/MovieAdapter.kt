package com.example.naversearchapistudy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlin.Exception

class MovieAdapter(val items: List<MovieItems>) : RecyclerView.Adapter<MovieAdapter.movieHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): movieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return movieHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: movieHolder, position: Int) {
        holder.bind(items[position])
    }


    class movieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image = itemView.findViewById<ImageView>(R.id.movie_image)
        val title = itemView.findViewById<TextView>(R.id.movie_title)
        val year = itemView.findViewById<TextView>(R.id.movie_year)
        val director = itemView.findViewById<TextView>(R.id.movie_director)
        val actor = itemView.findViewById<TextView>(R.id.movie_actor)


        fun bind(MovieData: MovieItems) {
            title.text = MovieData.title
            year.text = MovieData.year
            director.text = MovieData.director
            actor.text = MovieData.actor

            try {
                Glide.with(itemView)
                    .load(MovieData.image).into(image)

            } catch (e: Exception) {
                error(message = e.toString())
            }


        }
    }


}