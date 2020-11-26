package com.architecture.androidarchitecturestudy.adapter

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.architecture.androidarchitecturestudy.R
import com.architecture.androidarchitecturestudy.data.model.Movie
import com.architecture.androidarchitecturestudy.databinding.MovieItemBinding

class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {
    private val modelList = ArrayList<Movie>()
    private lateinit var binding: MovieItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.movie_item,
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    override fun getItemCount() = modelList.count()

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(this.modelList[position])
    }

    fun setItems(items: List<Movie>) {
        this.modelList.clear()
        this.modelList.addAll(processMovieItemString(items))
        notifyDataSetChanged()
    }
}