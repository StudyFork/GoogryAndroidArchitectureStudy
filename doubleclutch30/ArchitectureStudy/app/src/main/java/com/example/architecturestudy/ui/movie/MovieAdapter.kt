package com.example.architecturestudy.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.architecturestudy.R
import com.example.architecturestudy.model.MovieItems
import com.example.architecturestudy.ui.startWebView
import kotlinx.android.synthetic.main.item_movie.view.*
import java.lang.Exception

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    private val movieItem : MutableList<MovieItems> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieHolder(view)

    }

    override fun getItemCount(): Int {
        return movieItem.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(movieItem[position])
    }



    fun update(movieList : List<MovieItems>) {
        this.movieItem.clear()
        this.movieItem.addAll(movieList)
        notifyDataSetChanged()
    }

    class MovieHolder(view : View) : RecyclerView.ViewHolder(view) {

        lateinit var item: MovieItems

        init {
            itemView.setOnClickListener { view ->
                view.startWebView(item.link)
            }
        }

        fun bind(item : MovieItems) {
            this.item = item

            with(itemView) {
                movie_title.text = HtmlCompat.fromHtml(item.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
                movie_director.text = item.director
                movie_actor.text = item.actor

                try {
                    Glide.with(this).load(item.image).into(movie_image)
                } catch (e : Exception) {
                    error(message = e.toString())
                }

                movie_rating.rating = item.rating
            }

        }

    }


}