package com.example.hw2_project.recentSearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hw2_project.R
import com.example.hw2_project.data.repository.MovieRepositoryImpl
import com.example.hw2_project.databinding.RecentMovieItemBinding

class RecentSearchRecyclerViewAdapter(@Nullable private val recentSearchActivity: ClickSavedMovieListener) :
    RecyclerView.Adapter<RecentSearchRecyclerViewAdapter.ViewHolder>() {
    private val queryList = mutableListOf<String>()

    fun updateMovieList(list: List<String>?) {
        list?.run {
            queryList.clear()
            queryList.addAll(this)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val repository = MovieRepositoryImpl()
        val binding = DataBindingUtil.inflate<RecentMovieItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.recent_movie_item, parent, false
        )
        binding.vm = RecentSearchViewModel(repository)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return queryList.size
    }

    override fun onBindViewHolder(
        holder: RecentSearchRecyclerViewAdapter.ViewHolder, position: Int
    ) {
        val query = queryList[position]
        holder.bind(query)
    }

    inner class ViewHolder(private val recentMovieItemBinding: RecentMovieItemBinding) :
        RecyclerView.ViewHolder(recentMovieItemBinding.root) {
        fun bind(query: String) {
            recentMovieItemBinding.recentItemTextTitle.text = query
            recentMovieItemBinding.recentItemTextTitle.setOnClickListener {
                recentSearchActivity.onClickSavedMovie(query)
            }
            recentMovieItemBinding.executePendingBindings()
        }
    }

    interface ClickSavedMovieListener {
        fun onClickSavedMovie(movie: String)
    }

}