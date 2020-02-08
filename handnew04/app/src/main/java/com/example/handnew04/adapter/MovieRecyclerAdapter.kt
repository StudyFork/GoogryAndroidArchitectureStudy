package com.example.handnew04.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.handnew04.R
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
        // val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context))
        // inflate 할 때 기존 코드로 하면 parent 가 null 로 들어간다. 그래서 DataBindingUtil 을 사용하여 inflate 하여 parents 까지 넣어주어야 함.
        // R.layout.item_movie 를 사용하는 곳을 찾기도 힘듦.
        val binding = DataBindingUtil.inflate<ItemMovieBinding>(LayoutInflater.from(parent.context), R.layout.item_movie, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movies[position]
        holder.bind(item)
    }

    fun getMovieLink(position: Int): String = movies[position].link


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