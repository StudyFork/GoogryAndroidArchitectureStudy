package com.cnm.homework.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cnm.homework.R
import com.cnm.homework.data.model.NaverResponse
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(private val onClickAction: (NaverResponse.Item) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    val movieItems = mutableListOf<NaverResponse.Item>()

    fun setItem(item: List<NaverResponse.Item>) {
        movieItems.clear()
        movieItems.addAll(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = movieItems.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieItems[position])
    }

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            itemView.setOnClickListener {
                val item = movieItems[adapterPosition]
                onClickAction(item)
            }
        }

        fun bind(movieItem: NaverResponse.Item) {
            with(itemView)
            {
                Glide.with(this)
                    .load(movieItem.image)
                    .into(iv_movie_image)
                tv_movie_title.text = movieItem.title
                rb_movie_rating.rating = movieItem.userRating / 2
                tv_movie_pub_date.text = movieItem.pubDate
                tv_movie_director.text = movieItem.director
                tv_movie_actor.text = movieItem.actor
            }
        }
    }
}


