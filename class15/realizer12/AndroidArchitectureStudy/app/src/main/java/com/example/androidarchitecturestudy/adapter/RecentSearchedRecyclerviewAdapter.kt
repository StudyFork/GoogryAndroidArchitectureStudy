package com.example.androidarchitecturestudy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidarchitecturestudy.databinding.ItemSearchedListRecyclerviewBinding

class RecentSearchedRecyclerviewAdapter() : RecyclerView.Adapter<RecentSearchedRecyclerviewAdapter.RecentSearchedMovieItem>() {

    private val recentSearchMovieList = ArrayList<String>()
    private var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchedMovieItem {
        val binding = ItemSearchedListRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecentSearchedMovieItem(binding)
    }

    override fun onBindViewHolder(holder: RecentSearchedMovieItem, position: Int) {
        holder.bind(recentSearchMovieList[position])
    }

    override fun getItemCount(): Int = recentSearchMovieList.size

    //아이템 클릭 이벤트 받을  리스너 인터페이스
    interface OnItemClickListener {
        fun onItemClick(searchedQuery: String)
    }


    //외부에서  아이템 클릭 처리할 리스너
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    fun setRecentSearchedMovieData(recentSearchMovieList: List<String>) {
        this.recentSearchMovieList.clear()
        this.recentSearchMovieList.addAll(recentSearchMovieList)
        notifyDataSetChanged()
    }


    inner class RecentSearchedMovieItem(val binding: ItemSearchedListRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        // 각 뷰에  데이터 연결
        fun bind(recentSearchMovie: String?) {
            binding.searchMovieTitleData = recentSearchMovie
            binding.executePendingBindings()
            //검색된 쿼리 클릭
            binding.tvSearchedMovieTitle.setOnClickListener {
                recentSearchMovie?.let { it1 -> onItemClickListener?.onItemClick(searchedQuery = it1) }
            }
        }
    }


}