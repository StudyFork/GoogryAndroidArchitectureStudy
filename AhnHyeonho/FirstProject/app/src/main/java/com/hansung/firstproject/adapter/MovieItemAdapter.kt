package com.hansung.firstproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hansung.firstproject.data.MovieModel
import com.hansung.firstproject.databinding.MovieItemBinding

class MovieItemAdapter : BaseAdapter<MovieModel, MovieItemBinding>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<MovieModel> =
        MovieHolder(MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
}