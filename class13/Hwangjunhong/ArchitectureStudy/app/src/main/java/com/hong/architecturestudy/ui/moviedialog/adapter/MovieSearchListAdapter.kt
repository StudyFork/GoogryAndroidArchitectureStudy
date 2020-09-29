package com.hong.architecturestudy.ui.moviedialog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hong.architecturestudy.R
import com.hong.architecturestudy.data.source.local.entity.MovieInfo
import com.hong.architecturestudy.ui.main.MainViewModel
import com.hong.architecturestudy.ui.moviedialog.holder.MovieSearchListViewHolder

class MovieSearchListAdapter :
    RecyclerView.Adapter<MovieSearchListViewHolder>() {

    private val movieItems = mutableListOf<MovieInfo>()
    var vm: MainViewModel? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieSearchListViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_movie_search_list, parent, false
            )
        )

    override fun getItemCount() = movieItems.size

    override fun onBindViewHolder(holder: MovieSearchListViewHolder, position: Int) {
        holder.bind(movieItems[position], vm)
    }

    fun setList(movieInfo: List<MovieInfo>) {
        movieItems.clear()
        movieItems.addAll(movieInfo)
        notifyDataSetChanged()
    }
}