package com.olaf.nukeolaf

import android.os.Build
import android.text.Html
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movies_rv.view.*

class MovieItemViewHolder(itemView: View, private val itemListener: MovieItemListener) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(item: MovieItem) {
        itemView.apply {
            movie_title.text = item.title.htmlToString()
            movie_subtitle.text = item.subtitle
            movie_pub_date.text = item.pubDate
            movie_director.text = item.director.addCommas("감독 : ")
            movie_actor.text = item.actor.addCommas("출연진 : ")
            movie_rating.numStars = 5
            movie_rating.rating = item.userRating / 2
            setOnClickListener {
                itemListener.onMovieItemClick(item)
            }

            Glide.with(this)
                .load(item.image)
                .error(R.drawable.ic_launcher_foreground)
                .into(this.movie_image)
        }
    }

    private fun String.htmlToString(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
        } else {
            Html.fromHtml(this).toString()
        }
    }

    private fun String.addCommas(prefix: String): String {
        return if (this.isNotEmpty()) {
            this.substring(0, this.length - 1)
                .split("|")
                .joinToString(
                    prefix = prefix,
                    separator = ", "
                )
        } else {
            this
        }

    }

}