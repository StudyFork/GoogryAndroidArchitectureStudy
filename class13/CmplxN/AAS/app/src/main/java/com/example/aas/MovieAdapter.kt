package com.example.aas

import android.content.Intent
import android.net.Uri
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import java.util.concurrent.TimeUnit

class MovieAdapter(private val compositeDisposable: CompositeDisposable) :
	RecyclerView.Adapter<MovieViewHolder>() {
	private val movieList = mutableListOf<Movie>()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
		MovieViewHolder(parent).apply {
			RxView.clicks(itemView)
				.throttleFirst(1000L, TimeUnit.MILLISECONDS)
				.subscribe {
					val item = movieList[adapterPosition]
					val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
					itemView.context.startActivity(intent)
				}.addTo(compositeDisposable)
		}


	override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
		holder.bind(movieList[position])

	override fun getItemCount() = movieList.size

	fun setList(newMovieList: List<Movie>) {
		movieList.clear()
		movieList.addAll(newMovieList)
		notifyDataSetChanged()
	}
}