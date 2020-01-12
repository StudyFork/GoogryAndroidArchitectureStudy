package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.model.MovieResult
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieRecyclerViewAdpater(val itemClick: (movieDetail: String) -> Unit) :
    RecyclerView.Adapter<MovieRecyclerViewAdpater.MovieViewHolder>() {

    private val results = mutableListOf<MovieResult.Item>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieRecyclerViewAdpater.MovieViewHolder {
        val holder = MovieViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        )

        holder.itemView.setOnClickListener {
            itemClick(results[holder.adapterPosition].link)
        }

        return holder
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: MovieResult.Item) {
            with(itemView)
            {
                Glide.with(context).load(item.image).into(iv_movie)
                tv_movie_director.setText(item.director)
                tv_movie_title.setText(item.title)
                tv_publish_date.text = item.pubDate
                tv_actor.text = item.actor
                rating_bar_movie.rating = item.userRating / 2f
            }

        }
    }

    override fun getItemCount() = results.size

    override fun onBindViewHolder(holder: MovieRecyclerViewAdpater.MovieViewHolder, position: Int) {
        val element = results[position]
        holder.bind(element)
    }

    fun setItems(items: List<MovieResult.Item>) {
        results.clear()
        results.addAll(items)
        notifyDataSetChanged()
    }

}