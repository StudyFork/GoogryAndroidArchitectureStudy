package com.song2.myapplication.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.song2.myapplication.R
import com.song2.myapplication.source.MovieData

class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    private val data = ArrayList<MovieData>()

    fun setMovieList(newMovieList: List<MovieData>) {
        Log.e("setMovieList 데이터1", newMovieList.toString())
        with(data) {
            clear()
            Log.e("setMovieList 데이터", newMovieList.toString())
            addAll(newMovieList)
        }
        notifyDataSetChanged()
    }

    fun addItem(newMovieList: List<MovieData>){
        with(data) {
            addAll(newMovieList)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.rv_movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(data[position])
    }
}