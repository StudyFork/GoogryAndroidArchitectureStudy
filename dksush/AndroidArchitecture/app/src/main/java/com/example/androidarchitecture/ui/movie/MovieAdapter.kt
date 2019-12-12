package com.example.androidarchitecture.ui.movie

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.androidarchitecture.R
import com.example.androidarchitecture.data.response.MovieData
import com.example.androidarchitecture.ui.WebviewActivity
import com.example.androidarchitecture.ui.base.BaseRecyclerAdapter
import com.example.androidarchitecture.ui.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter : BaseRecyclerAdapter<MovieData, MovieAdapter.MovieHolder>(DiffCallback()){

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return MovieHolder(view)
    }

    inner class MovieHolder(view: View) : BaseViewHolder<MovieData>(view){
        lateinit var item : MovieData

        init {
            itemView.setOnClickListener {
                Intent(context, WebviewActivity::class.java).apply {
                    putExtra("link", item.link)
                }.run { context.startActivity(this) }
            }
        }

        override fun bind(item: MovieData) {
            this.item = item
            with(itemView){
                movie_title.text =
                    HtmlCompat.fromHtml(item.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
                director.text = item.director
                actors.text = item.actor
                pubDate.text = item.pubDate
                rating_star.rating = item.userRating

                try {
                    Glide.with(this)
                        .load(item.image)
                        .into(movie_image)
                } catch (e: Exception) {
                    Log.v("MovieAdapter", e.message!!)
                }

            }
        }

    }

}

private class DiffCallback : DiffUtil.ItemCallback<MovieData>() {
    // 두 객체가 같은 항목인지.
    override fun areItemsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
        return oldItem == newItem

    }

    // 두 항목의 데이터가 같은지.
    // areItemsTheSame 가 true 일떄만 호출.
    override fun areContentsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
        return oldItem == newItem


    }

}