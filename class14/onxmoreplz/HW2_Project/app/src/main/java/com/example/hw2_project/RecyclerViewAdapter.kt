package com.example.hw2_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerViewAdapter(private val movieList : MovieList) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(movieList.items.get(position))
    }

    override fun getItemCount(): Int {
        return movieList.items.count()
    }

    //ViewHolder
    class ViewHolder(val view : View) : RecyclerView.ViewHolder(view){

        val movie_img = itemView.findViewById<ImageView>(R.id.imageview_movie)
        val movie_title = itemView.findViewById<TextView>(R.id.textview_movie_title)
        val movie_pub = itemView.findViewById<TextView>(R.id.textview_movie_pubdate)
        val movie_director = itemView.findViewById<TextView>(R.id.textview_movie_description)

        fun bindItem( movie : Movie ){
            Glide.with(view.context).load(movie.image).into(movie_img)
            movie_title.text = movie.title
            movie_pub.text = movie.pubDate
            movie_director.text = movie.director
        }
    }

}