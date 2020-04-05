package com.olaf.nukeolaf

import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        var movieItem = intent.getSerializableExtra("movieItem") as MovieItem

        val title = "${movieItem.title} (${movieItem.pubDate})".htmlToString()
        val subtitle = "${movieItem.subtitle} (${movieItem.pubDate})"
        val director = "감독 : ${movieItem.director.replace("|", " ")}"
        val actor = "출연진 : ${movieItem.actor.replace("|", " ")}"

        movie_title.text = title
        movie_subtitle.text = subtitle
        movie_rating.numStars = 5
        movie_rating.rating = movieItem.userRating / 2
        movie_rating_num.text = movieItem.userRating.toString()
        movie_director.text = director
        movie_actor.text = actor
        movie_link.text = movieItem.link

        Glide.with(this)
            .load(movieItem.image)
            .into(movie_image)
    }

    fun String.htmlToString(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
        } else {
            Html.fromHtml(this).toString()
        }
    }
}
