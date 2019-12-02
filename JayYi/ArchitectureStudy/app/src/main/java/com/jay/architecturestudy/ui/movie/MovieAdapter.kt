package com.jay.architecturestudy.ui.movie

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jay.architecturestudy.R
import com.jay.architecturestudy.model.Movie
import com.jay.architecturestudy.ui.WebViewActivity
import com.jay.architecturestudy.ui.WebViewActivity.Companion.EXTRA_URL
import kotlinx.android.synthetic.main.list_item_movie.view.*
import java.lang.Exception

internal class MovieAdapter(private val context: Context) : RecyclerView.Adapter<MovieHolder>() {
    private var data = arrayListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_movie, parent, false)
        return MovieHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(data[position])
    }

    fun setData(movies: List<Movie>) {
        data.clear()
        data.addAll(movies)
        notifyDataSetChanged()
    }

}

internal class MovieHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {

    fun bind(model: Movie) {
        with(view) {
            movie_title.text = HtmlCompat.fromHtml(model.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
            movie_director.text = model.director
            movie_actor.text = model.actor

            try {
                Glide.with(this)
                    .load(model.image)
                    .into(movie_poster)
            } catch (e: Exception) {
                Log.e("MovieAdapter", "error=${e.message}")
            }

            user_rating.rating = model.userRating

            setOnClickListener {
                val context = view.context
                Intent(context, WebViewActivity::class.java).apply {
                    putExtra(EXTRA_URL, model.link)
                }.run {
                    context.startActivity(this)
                }
            }
        }
    }
}