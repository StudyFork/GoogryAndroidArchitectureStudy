package com.example.aas

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {
	private val movieList = mutableListOf<ApiResult.Movie>()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
		MovieViewHolder.createViewHolder(parent)

	override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
		holder.bind(movieList[position])

	override fun getItemCount() = movieList.size

	fun setList(newMovieList: List<ApiResult.Movie>) {
		movieList.clear()
		movieList.addAll(newMovieList)
		notifyDataSetChanged()
	}
}