package com.world.tree.architecturestudy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(context: Context) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private lateinit var movies : List<Movie.Item>
    private val context = context
    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val v : View = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(v)
    }

    override fun getItemCount(): Int {
        if (!::movies.isInitialized)  return 0
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        Glide.with(context).load(movies[position].image)
            .centerCrop()
            .into(holder.itemView.imgPoster)
        holder.itemView.txtTitle.text = movies[position].title
        holder.itemView.txtDirector.text = movies[position].director
        holder.itemView.txtActor.text = movies[position].actor
    }

    fun setDataList(data: List<Movie.Item>) {
        this.movies = data
        notifyDataSetChanged()
    }
}