package com.example.aas

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
	LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
) {

	private val movieImage = itemView.img_movie
	private val movieTitle = itemView.tv_title
	private val movieSubTitle = itemView.tv_subtitle
	private val movieActor = itemView.tv_actor
	private val movieRating = itemView.tv_rating

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
}