package com.hong.architecturestudy.ui

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.hong.architecturestudy.R
import com.hong.architecturestudy.data.MovieData
import com.hong.architecturestudy.ext.loadGlideImageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_movie_list.view.*

class MovieViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_movie_list, parent, false)) {

    fun onBind(movieData: MovieData){
        itemView.apply {
            tv_actor.text = movieData.actor
            tv_director.text = movieData.director
            tv_opening_date.text = movieData.pubDate
            tv_title.text = HtmlCompat.fromHtml(movieData.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
            rating_bar.rating = (movieData.userRating.toFloatOrNull() ?: 0f) / 2
            iv_poster.loadGlideImageView(movieData.image)

            setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(movieData.link))
                context.startActivity(intent)
            }

        }
    }

}