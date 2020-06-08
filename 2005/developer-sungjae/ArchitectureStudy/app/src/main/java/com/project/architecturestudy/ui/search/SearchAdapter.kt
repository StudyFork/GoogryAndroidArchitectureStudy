package com.project.architecturestudy.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.project.architecturestudy.R
import com.project.architecturestudy.data.model.MovieItem
import com.project.architecturestudy.databinding.MovieItemBinding

class SearchAdapter : RecyclerView.Adapter<SearchingResultHolder>() {

    private val movieList: MutableList<MovieItem> = mutableListOf()
    lateinit var onClick: ((MovieItem) -> Unit)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchingResultHolder {
        return SearchingResultHolder(parent).apply {
            itemView.setOnClickListener {
                val item = movieList[adapterPosition]
                onClick.invoke(item)
            }
        }
    }

    override fun onBindViewHolder(holder: SearchingResultHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int = movieList.count()

    fun addMovieData(movieItemList: List<MovieItem>) {
        this.movieList.clear()
        this.movieList.addAll(movieItemList)
        notifyDataSetChanged()
    }
}

class SearchingResultHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
) {
    private val binding: MovieItemBinding = DataBindingUtil.bind(itemView)!!

    fun bind(movieItem: MovieItem) {
        binding.movieItem = movieItem
        binding.executePendingBindings()
    }
}

