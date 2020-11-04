package com.architecture.androidarchitecturestudy.adapter

import android.content.Intent
import android.net.Uri
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.architecture.androidarchitecturestudy.R
import com.architecture.androidarchitecturestudy.model.Movie
import com.bumptech.glide.Glide

@Suppress("DEPRECATION")
class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
    lateinit var tempData: Movie

    private val image: ImageView = view.findViewById(R.id.iv_movie_item)
    private val title: TextView = view.findViewById(R.id.tv_movie_item_title)
    private val rating: RatingBar = view.findViewById(R.id.rb_movie_item_rating)
    private val director: TextView = view.findViewById(R.id.tv_movie_item_director)
    private val pubDate: TextView = view.findViewById(R.id.tv_movie_item_pub_date)
    private val actor: TextView = view.findViewById(R.id.tv_rv_movie_item_actor)

    override fun onClick(view: View?) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(tempData.link))
        this.itemView.context.startActivity(intent)
    }

    fun onBind(movieData: Movie) {
        tempData = movieData
        title.text = Html.fromHtml(movieData.title)
        pubDate.text = movieData.pubDate
        actor.text = movieData.actor
        director.text = movieData.director
        rating.rating = (movieData.userRating!! / 2).toFloat()
        Glide.with(itemView.context).load(movieData.image).into(image)
        itemView.setOnClickListener {
            onClick(itemView)
        }
    }
}