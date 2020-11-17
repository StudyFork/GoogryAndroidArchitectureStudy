package com.example.hw2_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hw2_project.data.api.NaverMovieData

class RecyclerViewAdapter() : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){

    private val arrListOfMovie :ArrayList<NaverMovieData.NaverMovie> = ArrayList()

    fun movieListChange(movies : List<NaverMovieData.NaverMovie>){
        this.arrListOfMovie.clear()
        this.arrListOfMovie.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(arrListOfMovie.get(position))
    }

    override fun getItemCount(): Int {
        return arrListOfMovie.size
    }

    //ViewHolder
    class ViewHolder(val view : View) : RecyclerView.ViewHolder(view){

        private val movie_img = itemView.findViewById<ImageView>(R.id.imageview_movie)
        private val movie_title = itemView.findViewById<TextView>(R.id.textview_movie_title)
        private val movie_pub = itemView.findViewById<TextView>(R.id.textview_movie_pubdate)
        private val movie_director = itemView.findViewById<TextView>(R.id.textview_movie_director)

        fun bindItem( movie : NaverMovieData.NaverMovie){
            Glide.with(view.context)
                .load(movie.image)
                .error(R.mipmap.ic_launcher)
                .into(movie_img)

            movie_title.text = movie.title
            movie_pub.text = movie.pubDate
            movie_director.text = movie.director
        }
    }

}