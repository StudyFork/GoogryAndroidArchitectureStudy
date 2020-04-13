package com.example.studyforkandroid.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.studyforkandroid.R
import com.example.studyforkandroid.data.Movie
import com.example.studyforkandroid.utils.htmlToString
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieVh>() {

    private val movieList = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVh {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        val movieVh = MovieVh(view)
        view.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(movieList[movieVh.adapterPosition].link)
            parent.context.startActivity(intent)
        }

        return movieVh
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun addAll(movieList: List<Movie>) {
        this.movieList.addAll(movieList)
    }

    fun clear() {
        this.movieList.clear()
    }

    override fun onBindViewHolder(holder: MovieVh, position: Int) {
        movieList[position].let { movie ->
            with(holder) {
                title.text = movie.title.htmlToString()
                rating.text = movie.userRating.htmlToString()
                actor.text = movie.actor.htmlToString().replace("|", " ")
            }
        }

        Glide.with(holder.img.context).load(movieList[position].image).into(holder.img)
    }

    class MovieVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.movieImg
        val title: TextView = itemView.movieTitle
        val actor: TextView = itemView.movieActor
        val rating: TextView = itemView.movieRating
    }
}