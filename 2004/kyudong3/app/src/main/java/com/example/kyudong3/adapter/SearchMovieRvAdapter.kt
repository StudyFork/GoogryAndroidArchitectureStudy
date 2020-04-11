package com.example.kyudong3.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kyudong3.R
import com.example.kyudong3.extension.htmlToString
import com.example.kyudong3.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class SearchMovieRvAdapter :
    RecyclerView.Adapter<SearchMovieRvAdapter.SearchMovieVH>() {

    private val movieList = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieVH {
        return SearchMovieVH(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: SearchMovieVH, position: Int) {
        holder.bind(movieList[position])
    }

    fun setMovieList(movieList: List<Movie>) {
        this.movieList.apply {
            clear()
            addAll(movieList)
        }
    }

    inner class SearchMovieVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var movie: Movie

        init {
            itemView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(movie.link))
                itemView.context.startActivity(intent)
            }
        }

        val image: ImageView = itemView.movieImageImv
        val title: TextView = itemView.movieTitleTxv
        val subTitle: TextView = itemView.movieSubTitleTxv
        val pubDate: TextView = itemView.moviePubDateTxv
        val director: TextView = itemView.movieDirectorTxv
        val actor: TextView = itemView.movieActorTxv
        val userRating: TextView = itemView.movieUserRatingTxv

        fun bind(movie: Movie) {
            this.movie = movie
            Glide.with(itemView.context).load(movie.image).into(image)
            title.text = movie.title.htmlToString()
            subTitle.text = movie.subtitle.htmlToString()
            pubDate.text = movie.pubDate.toString().htmlToString()
            director.text = movie.director.htmlToString().replace("|", "")
            actor.text = movie.actor.htmlToString().replace("|", " ")
            userRating.text = "평점 : ${movie.userRating.toString().htmlToString()}"
        }
    }
}