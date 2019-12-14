package com.example.architecturestudy.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.architecturestudy.R
import com.example.architecturestudy.model.KinItems
import com.example.architecturestudy.model.MovieItems
import kotlinx.android.synthetic.main.item_movie.view.*
import java.lang.Exception

class MovieAdapter(val items : List<MovieItems>) : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    private val movieList : MutableList<MovieItems> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieHolder(view)

    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(items[position])
    }



    fun upDate(movieList : List<MovieItems>) {
        this.movieList.clear()
        this.movieList.addAll(movieList)
        notifyDataSetChanged()
    }

    class MovieHolder(view : View) : RecyclerView.ViewHolder(view) {

        fun bind(item : MovieItems) {
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