package com.studyfork.architecturestudy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieResultRVAdapter : RecyclerView.Adapter<MovieResultRVAdapter.MovieResultVH>() {

    private val items = mutableListOf<MovieResponse.Item>()

    fun setItems(items: List<MovieResponse.Item>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieResultVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieResultVH(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MovieResultVH, position: Int) {
        holder.bind(this.items[position])
    }


    inner class MovieResultVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: MovieResponse.Item) {
            Glide.with(itemView.context).load(item.image).into(itemView.iv_movie_image)
            itemView.tv_movie_name.text = item.title
            itemView.rb_movie_rating.rating = item.userRating
            itemView.tv_movie_publish_date.text = item.pubDate
            itemView.tv_movie_director.text = item.director
            itemView.tv_movie_actor.text = item.actor
        }
    }
}