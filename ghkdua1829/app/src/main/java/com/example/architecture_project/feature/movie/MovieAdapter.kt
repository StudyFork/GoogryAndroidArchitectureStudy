package com.example.architecture_project.feature.movie

import android.text.Html
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

class MovieAdapter(private val clickListener: MovieViewHolder.ItemClickListener) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private val movieData: ArrayList<Movie> = ArrayList()

    fun setMovieItemList(newMovieData: ArrayList<Movie>) {
        with(movieData) {
            clear()
            addAll(newMovieData)
        }
        notifyDataSetChanged()
    }

    fun getMovieLink(position: Int): String {
        return movieData[position].link
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view, clickListener)
    }

    override fun getItemCount(): Int {
        return movieData.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieData[position])
    }


    class MovieViewHolder(view: View, private val clickListener: ItemClickListener) :
        RecyclerView.ViewHolder(view) {

        private val image: ImageView =
            view.findViewById(R.id.rv_item_iv_movie_image)
        private val title: TextView =
            view.findViewById(R.id.rv_item_tv_movie_title)
        private val userRating: RatingBar =
            view.findViewById(R.id.rv_item_rb_movie_rating)
        private val pubDate: TextView =
            view.findViewById(R.id.rv_item_tv_movie_pubDate)
        private val director: TextView =
            view.findViewById(R.id.rv_item_tv_movie_director)
        private val actor: TextView =
            view.findViewById(R.id.rv_item_tv_movie_actor)
        private lateinit var link: String

        init {
            view.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }

        interface ItemClickListener {
            fun onItemClick(position: Int)
        }

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