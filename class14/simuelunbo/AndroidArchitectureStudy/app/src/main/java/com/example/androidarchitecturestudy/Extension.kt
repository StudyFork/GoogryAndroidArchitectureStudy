package com.example.androidarchitecturestudy

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidarchitecturestudy.data.model.Movie
import com.example.androidarchitecturestudy.data.model.QueryHistory
import com.example.androidarchitecturestudy.ui.MovieAdapter
import com.example.androidarchitecturestudy.ui.TitleAdapter

@BindingAdapter("movieList")
fun RecyclerView.bindMovieList(items: List<Movie>) {
    (adapter as MovieAdapter).setMovieList(items)
}

@BindingAdapter("searchList")
fun RecyclerView.bindQueryList(items: List<QueryHistory>) {
    (adapter as TitleAdapter).setTitleList(items)
}


@BindingAdapter("imageUrl")
fun ImageView.loadUrl(imageUrl: String) {
    Glide.with(this)
        .load(imageUrl)
        .into(this)
}

@BindingAdapter("title")
fun TextView.setTitleText(title: String) {
    this.text = title.replace("<b>", "").replace("</b>", "")
}

@BindingAdapter("director")
fun TextView.setDirector(director: String) {
    this.text = director.replace("|", "")
}

@BindingAdapter("isVisibility")
fun ProgressBar.setVisibleOrGone(isVisibility: Boolean) {
    if (isVisibility) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

fun Context.link(context: Context, url: String) {
    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}
