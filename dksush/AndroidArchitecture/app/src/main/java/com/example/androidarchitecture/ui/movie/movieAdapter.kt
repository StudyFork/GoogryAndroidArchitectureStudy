package com.example.androidarchitecture.ui.movie

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

class movieAdapter(
    val items: List<ResponseMovie>
) : RecyclerView.Adapter<movieAdapter.movieHolder>() {


    override fun getItemCount(): Int {
        return items.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): movieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return movieHolder(view)
    }


    override fun onBindViewHolder(holder: movieHolder, position: Int) {
        holder.bind(items[position])
    }


    inner class movieHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(model:ResponseMovie){
            with(view){
                movie_title.text = HtmlCompat.fromHtml(model.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
                director.text = model.director
                actors.text = model.actor
                pubDate.text = model.pubDate
                rating_star.rating = model.userRating

                try {
                    Glide.with(this)
                        .load(model.image)
                        .into(movie_image)
                }catch (e:Exception){
                    Log.v("MovieAdapter", e.message)
                }

                setOnClickListener(){
                    Intent(context, WebviewActivity::class.java).apply {
                        putExtra("link", model.link)
                    }.run { context.startActivity(this) }
                }



            }
        }
    }

}