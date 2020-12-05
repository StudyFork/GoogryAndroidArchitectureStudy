package com.architecture.androidarchitecturestudy.adapter


import android.widget.ImageView
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.architecture.androidarchitecturestudy.data.model.Movie
import com.architecture.androidarchitecturestudy.data.model.SearchHistoryEntity
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun imageUrl(view: ImageView, url: String?) {
    Glide.with(view)
        .load(url)
        .into(view)
}

@BindingAdapter("bindRatingStar")
fun bindRatingStar(view: RatingBar, userRating: Double) {
    view.rating = (userRating / 2).toFloat()
}

@BindingAdapter("setMovieList")
fun RecyclerView.setMovieList(list: List<Movie>?) {
    list ?: return
    val movieAdapter =
        adapter as? MovieAdapter ?: MovieAdapter().apply { adapter = this }
    movieAdapter.setMovieList(list)
}

@BindingAdapter("setSearchHistories")
fun RecyclerView.setSearchHistories(list: List<SearchHistoryEntity>?) {
    list ?: return
    val historyAdapter =
        adapter as? SearchHistoryAdapter ?: SearchHistoryAdapter().apply { adapter = this }
    historyAdapter.setSearchHistories(list)
}