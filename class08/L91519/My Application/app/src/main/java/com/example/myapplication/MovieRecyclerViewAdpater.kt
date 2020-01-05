package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.MovieResult
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieRecyclerViewAdpater: RecyclerView.Adapter<MovieRecyclerViewAdpater.MovieViewHolder>() {

    private val results = mutableListOf<MovieResult.Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieRecyclerViewAdpater.MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)

        return MovieViewHolder(view)
    }

    class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: MovieResult.Item) {
            itemView.iv_movie.setBackgroundColor(0)
            itemView.tv_movie_detail.setText("detail")
            itemView.tv_movie_director.setText(item.director)
            itemView.tv_movie_title.setText(item.subtitle)

        }
    }

    override fun getItemCount() = results.size

    override fun onBindViewHolder(holder: MovieRecyclerViewAdpater.MovieViewHolder, position: Int) {
        val element = results[position]
        holder.bind(element)
    }

    fun setItems(itmes: List<MovieResult.Item>)
    {
        results.clear()
        results.addAll(itmes)
        notifyDataSetChanged()
    }

}