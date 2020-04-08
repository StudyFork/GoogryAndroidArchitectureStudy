package com.eunice.eunicehong.ui

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eunice.eunicehong.R
import com.eunice.eunicehong.data.remote.Movie

class MovieAdapter(private val items: ArrayList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
    )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items.get(position).let { movie ->
            with(holder) {
                title.text = HtmlCompat.fromHtml(movie.title, HtmlCompat.FROM_HTML_MODE_LEGACY)

                subtitle.text = HtmlCompat.fromHtml(
                    movie.subtitle,
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )

                actors.text = movie.actors.formatStaffList()

                directors.text = movie.directors.formatStaffList()

                pubDate.text = movie.pubDate
                rating.text = movie.userRating

                Glide.with(poster.context).load(movie.imageUrl).into(poster)

                card.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(movie.link))
                    card.context.startActivity(intent)
                }
            }
        }
    }

    private fun String.formatStaffList(): String = this.split("|")
        .map { it.trim() }
        .filter { it != "" }
        .joinToString(", ")


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card = itemView.findViewById<CardView>(R.id.movie_card)
        val title = itemView.findViewById<TextView>(R.id.movie_title)
        val poster = itemView.findViewById<ImageView>(R.id.movie_poster)
        val subtitle = itemView.findViewById<TextView>(R.id.movie_subtitle)
        val actors = itemView.findViewById<TextView>(R.id.movie_actors)
        val directors = itemView.findViewById<TextView>(R.id.movie_directors)
        val pubDate = itemView.findViewById<TextView>(R.id.movie_date_text)
        val rating = itemView.findViewById<TextView>(R.id.movie_user_rating_value)
    }

}