package com.example.aas

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

	private val movieImage = view.findViewById<ImageView>(R.id.img_movie)
	private val movieTitle = view.findViewById<TextView>(R.id.tv_title)
	private val movieSubTitle = view.findViewById<TextView>(R.id.tv_subtitle)
	private val movieActor = view.findViewById<TextView>(R.id.tv_actor)
	private val movieRating = view.findViewById<TextView>(R.id.tv_rating)

	fun bind(movie: ApiResult.Movie) {
		Glide.with(movieImage.context)
			.load(movie.image)
			.centerCrop()
			.into(movieImage)

		movieTitle.text = interpretHtml(movie.title)
		movieSubTitle.text = interpretHtml(movie.subtitle)
		movieActor.text = interpretHtml(movie.actor)
		movieRating.text = interpretHtml(movie.userRating)

		itemView.setOnClickListener {
			val intent = Intent(Intent.ACTION_VIEW, Uri.parse(movie.link))
			it.context.startActivity(intent)
		}
	}

	companion object {
		fun createViewHolder(parent: ViewGroup): MovieViewHolder {
			val view =
				LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
			return MovieViewHolder(view)
		}
	}
}