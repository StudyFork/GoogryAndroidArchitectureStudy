package com.example.hw2_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hw2_project.data.Movie

class RecyclerViewAdapter() : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){

    private val arrListOfMovie :ArrayList<Movie> = ArrayList()

    fun movieListChange(movies : List<Movie>){
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

        fun bindItem( movie : Movie){
            if( movie.image != ""){
                Glide.with(view.context).load(movie.image).into(movie_img)
            }else{ // 이미지가 없는 경우, movie_pic 안드로이드 기본 아이콘으로 설정
                movie_img?.setImageResource(R.mipmap.ic_launcher)
            }
            movie_title.text = movie.title
            movie_pub.text = movie.pubDate
            movie_director.text = movie.director
        }
    }

}