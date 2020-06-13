package com.hwaniiidev.architecture.ui.main

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hwaniiidev.architecture.R
import com.hwaniiidev.architecture.databinding.ItemMovieListBinding
import com.hwaniiidev.architecture.model.Item


class AdapterMovieList :
    RecyclerView.Adapter<AdapterMovieList.MovieHolder>() {
    val movieList: ArrayList<Item> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemMovieListBinding>(
            inflater,
            R.layout.item_movie_list,
            parent,
            false
        )

        val holder = MovieHolder(binding)
        //클릭시 브라우저로 영화 정보 조회
        holder.itemView.setOnClickListener { v ->
            val intent =
                Intent(Intent.ACTION_VIEW, Uri.parse(movieList.get(holder.adapterPosition).link))
            holder.itemView.context.startActivity(intent)
        }
        return holder
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val itemData = movieList.get(position)

        holder.binding.movieItem = itemData
        holder.binding.executePendingBindings()
    }

    fun addItem(items: List<Item>) {
        movieList.clear()
        movieList.addAll(items)
        notifyDataSetChanged()
    }

    inner class MovieHolder(val binding: ItemMovieListBinding) :
        RecyclerView.ViewHolder(binding.root)
}