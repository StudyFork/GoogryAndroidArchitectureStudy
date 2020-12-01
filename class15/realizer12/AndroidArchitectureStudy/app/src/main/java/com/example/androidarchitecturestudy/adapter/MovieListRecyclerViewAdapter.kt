package com.example.androidarchitecturestudy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidarchitecturestudy.data.GetMovieInfo
import com.example.androidarchitecturestudy.databinding.ItemMainRecyclerviewBinding

class MovieListRecyclerViewAdapter() : RecyclerView.Adapter<MovieListRecyclerViewAdapter.MovieItemViewHolder>() {


    private val movieList = ArrayList<GetMovieInfo.MovieData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val binding = ItemMainRecyclerviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieItemViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bind(movieList[position])
    }


    override fun getItemCount(): Int = movieList.size


    fun setMovieData(movieList: List<GetMovieInfo.MovieData>) {
        this.movieList.clear()
        this.movieList.addAll(movieList)
        notifyDataSetChanged()
    }

    inner class MovieItemViewHolder(val binding:ItemMainRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        // 각 뷰에  데이터 연결
        fun bind(movieData: GetMovieInfo.MovieData?) {

            //아이템 뷰  데이터 연결
            binding.movieData = movieData

        }
    }

}