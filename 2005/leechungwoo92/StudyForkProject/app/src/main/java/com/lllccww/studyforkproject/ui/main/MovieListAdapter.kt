package com.lllccww.studyforkproject.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lllccww.studyforkproject.R
import com.lllccww.studyforkproject.data.model.MovieItem
import com.lllccww.studyforkproject.databinding.ItemMovieListBinding

class MovieListAdapter(private val itemClick: (MovieItem) -> Unit) :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {
    private val list: ArrayList<MovieItem> = ArrayList()
    private lateinit var binding: ItemMovieListBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie_list,
            parent,
            false
        )

        val viewHolder = ViewHolder(binding)
        binding.root.setOnClickListener {
            itemClick(list[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])


    }

    fun addItems(items: List<MovieItem>) {
        list.clear()
        list.addAll(items)
        Log.d("movieData : ", list.size.toString())
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemBinding: ItemMovieListBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(movieItem: MovieItem) {
            binding.movieItem = movieItem
            binding.executePendingBindings()
        }


    }
}