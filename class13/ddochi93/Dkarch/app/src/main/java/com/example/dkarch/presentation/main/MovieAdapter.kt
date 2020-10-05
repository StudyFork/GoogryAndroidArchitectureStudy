package com.example.dkarch.presentation.main

import android.net.Uri
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dkarch.R
import com.example.dkarch.data.entity.Movie
import com.example.dkarch.domain.util.loadUri
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

    fun submitList(movieList: ArrayList<Movie>) {
        this.movieList.clear()
        this.movieList.addAll(movieList)
        notifyDataSetChanged()
    }

    class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
    ) {
        private val movieImage: ImageView = itemView.movie_img
        private val titleTextView: TextView = itemView.tv_title
        private val subtitleTextView: TextView = itemView.tv_sub_title
        private val actorTextView: TextView = itemView.tv_actor
        private val ratingsTextView: TextView = itemView.tv_rating

        fun bind(item: Movie, isClicked: (String) -> Unit) {
            movieImage.loadUri(Uri.parse(item.image))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                titleTextView.text = Html.fromHtml(item.title, Html.FROM_HTML_MODE_LEGACY)
            } else {
                titleTextView.text = Html.fromHtml(item.title)
            }
            subtitleTextView.text = item.subtitle
            actorTextView.text = item.actor
            ratingsTextView.text = item.userRating
            itemView.setOnClickListener { isClicked(item.link) }
        }
    }

}
