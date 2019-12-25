package com.example.architecture_project.feature.movie

import android.content.Intent
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.architecture_project.R
import com.example.architecture_project.data.Movie
import com.example.architecture_project.feature.search.WebviewActivity

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var movieData: ArrayList<Movie> = ArrayList()

    fun setMovieItemList(movieData: ArrayList<Movie>) {
        this.movieData = movieData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)

        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieData.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieData[position])
        holder.itemView.setOnClickListener {
            Log.e("link is ", movieData[position].link)
            val goWebView = Intent(holder.itemView.context, WebviewActivity::class.java)
            goWebView.putExtra("url", movieData[position].link)
            holder.itemView.context.startActivity(goWebView)
        }
    }

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val image: ImageView = view.findViewById(R.id.rv_item_iv_movie_image)
        private val title: TextView = view.findViewById(R.id.rv_item_tv_movie_title)
        private val userRating: RatingBar = view.findViewById(R.id.rv_item_rb_movie_rating)
        private val pubDate: TextView = view.findViewById(R.id.rv_item_tv_movie_pubDate)
        private val director: TextView = view.findViewById(R.id.rv_item_tv_movie_director)
        private val actor: TextView = view.findViewById(R.id.rv_item_tv_movie_actor)
        private lateinit var link: String

        fun bind(data: Movie) {
            Glide.with(itemView).load(data.image).into(image)
            title.text = Html.fromHtml(data.title)
            userRating.rating = data.rating / 2
            pubDate.text = data.pubDate
            director.text = data.director
            actor.text = data.actor
            link = data.link
        }
    }
}