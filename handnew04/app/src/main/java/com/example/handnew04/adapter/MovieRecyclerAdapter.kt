package com.example.handnew04.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.handnew04.data.MovieData
import com.example.handnew04.databinding.ItemMovieBinding


class MovieRecyclerAdapter :
    RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder>() {
    private val movies: ArrayList<MovieData> = ArrayList()

    fun setItemList(movies: ArrayList<MovieData>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movies.get(position)
        holder.bind(item)
    }

    fun getMovieLink(position: Int): String = movies.get(position).link


    interface ItemClickListener {
        fun onClick(position: Int)
    }


    lateinit var itemClickListner: ItemClickListener


    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListner = itemClickListener
    }


    inner class ViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener(View.OnClickListener {
                itemClickListner.onClick(adapterPosition)
            })
        }

        fun bind(movieData: MovieData) {
            binding.movie = movieData
        }
    }
}