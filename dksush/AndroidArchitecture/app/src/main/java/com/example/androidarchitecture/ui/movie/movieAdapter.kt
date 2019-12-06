package com.example.androidarchitecture.ui.movie

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidarchitecture.R
import com.example.androidarchitecture.models.ResponseMovie
import com.example.androidarchitecture.ui.WebviewActivity
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(val context: Context) : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {


    private val data = arrayListOf<ResponseMovie>()

    override fun getItemCount(): Int {
        return data.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return MovieHolder(view)
    }


    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(data[position])

    }


    fun setData(items: List<ResponseMovie>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()


    }


    inner class MovieHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        lateinit var model: ResponseMovie

        init { // 클릭 리스너 한번만 세팅.
            view.setOnClickListener() {
                Intent(context, WebviewActivity::class.java).apply {
                    putExtra("link", model.link)
                }.run { context.startActivity(this) }
            }
        }

        fun bind(model: ResponseMovie) {
            this.model = model

            with(view) {
                movie_title.text =
                    HtmlCompat.fromHtml(model.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
                director.text = model.director
                actors.text = model.actor
                pubDate.text = model.pubDate
                rating_star.rating = model.userRating

                try {
                    Glide.with(this)
                        .load(model.image)
                        .into(movie_image)
                } catch (e: Exception) {
                    Log.v("MovieAdapter", e.message!!)
                }

            }
        }
    }

}