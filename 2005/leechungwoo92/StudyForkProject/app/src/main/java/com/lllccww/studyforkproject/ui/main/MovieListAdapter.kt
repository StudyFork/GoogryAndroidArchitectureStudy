package com.lllccww.studyforkproject.ui.main

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lllccww.studyforkproject.R
import com.lllccww.studyforkproject.data.model.MovieItem
import com.lllccww.studyforkproject.databinding.ItemMovieListBinding

class MovieListAdapter :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {
    private val list: ArrayList<MovieItem> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemMovieListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_movie_list,
            parent,
            false
        )

        return ViewHolder(binding)
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


    inner class ViewHolder(private val binding: ItemMovieListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(binding.movieItem!!.link))
                it.context.startActivity(intent)
            }
        }


        fun bind(movieItem: MovieItem) {
            binding.movieItem = movieItem
            binding.executePendingBindings()
        }


    }
}