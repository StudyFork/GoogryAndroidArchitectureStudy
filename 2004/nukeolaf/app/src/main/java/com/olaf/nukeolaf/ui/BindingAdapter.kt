package com.olaf.nukeolaf.ui

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.olaf.nukeolaf.R
import com.olaf.nukeolaf.data.model.MovieItem

@BindingAdapter("movieImage")
fun loadMovieImage(view: ImageView, imageUrl: String?) {
    Glide.with(view.context)
        .load(imageUrl)
        .error(R.drawable.ic_launcher_foreground)
        .into(view)
}

@BindingAdapter("setOnQueryTextListener")
fun setOnQueryTextListener(view: SearchView, viewModel: MainViewModel) {
    view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            viewModel.searchMovie(query)
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }
    })
}

@BindingAdapter("setClickListener")
fun setClickListener(view: ConstraintLayout, url: String?) {
    if (url != null) {
        view.setOnClickListener {
            Intent(Intent.ACTION_VIEW, Uri.parse(url)).also {
                view.context.startActivity(it)
            }
        }
    }
}

@BindingAdapter("setItems")
fun setItems(view: RecyclerView, items: List<MovieItem>) {
    val adapter = view.adapter as? MovieAdapter ?: MovieAdapter().apply { view.adapter = this }
    adapter.setMovies(items)
}

@BindingAdapter("app:movieStringVisibility")
fun movieStringVisibility(view: View, string: String) {
    view.visibility = if (string.isNullOrBlank()) View.GONE else View.VISIBLE
}