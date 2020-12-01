package com.example.androidarchitecturestudy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidarchitecturestudy.databinding.ItemSearchedListRecyclerviewBinding

class RecentSearchedRecyclerviewAdapter() : RecyclerView.Adapter<RecentSearchedRecyclerviewAdapter.RecentSearchedMovieItem>() {

    private val recentSearchMovieList = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchedMovieItem {
        val binding = ItemSearchedListRecyclerviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RecentSearchedMovieItem(binding)
    }

    override fun onBindViewHolder(holder: RecentSearchedMovieItem, position: Int) {
        holder.bind(recentSearchMovieList[position])
    }

    override fun getItemCount(): Int = recentSearchMovieList.size


    fun setRecentSearchedMovieData(recentSearchMovieList: List<String>) {
        this.recentSearchMovieList.clear()
        this.recentSearchMovieList.addAll(recentSearchMovieList)
        notifyDataSetChanged()
    }


    inner class RecentSearchedMovieItem(val binding: ItemSearchedListRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        // 각 뷰에  데이터 연결
        fun bind(recentSearchMovie: String?) {
            binding.searchMovieTitleData =recentSearchMovie
        }
    }


}