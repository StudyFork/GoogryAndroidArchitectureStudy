package com.song2.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.song2.myapplication.R
import com.song2.myapplication.databinding.RvMovieItemBinding
import com.song2.myapplication.source.MovieData

class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    private val data = ArrayList<MovieData>()
    private lateinit var binding: RvMovieItemBinding

    fun clearData() {
        data.clear()
    }

    fun addItem(newMovieList: List<MovieData>?) {
        if (newMovieList == null) return

        with(data) {
            addAll(newMovieList!!)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.rv_movie_item,
            parent,
            false
        )

        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(data[position])
    }
}