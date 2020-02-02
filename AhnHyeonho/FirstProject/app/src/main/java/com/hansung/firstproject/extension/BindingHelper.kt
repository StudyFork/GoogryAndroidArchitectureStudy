package com.hansung.firstproject.extension

import android.os.Build
import android.text.Html
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hansung.firstproject.R
import com.hansung.firstproject.adapter.MovieItemAdapter
import com.hansung.firstproject.data.MovieModel


@BindingAdapter(value = ["loadUrl"])
fun ImageView.loadUrl(url: String) =
    Glide.with(context)
        .load(url)
        .apply(RequestOptions().override(300, 450).fitCenter())
        .error(R.drawable.default_poster)
        .into(this)

@BindingAdapter(value = ["setRating"])
fun RatingBar.setStrRating(userRating: String) {
    this.rating = userRating.toFloat() / 2
}

@BindingAdapter(value = ["setTitle"])
fun TextView.setTitle(title: String) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
        this.text = Html.fromHtml(title)
    } else {
        this.text = Html.fromHtml(title, Html.FROM_HTML_MODE_LEGACY)
    }
}

@BindingAdapter(value = ["refreshMovieData"])
fun RecyclerView.refreshMovieData(items: ArrayList<MovieModel>) {
    (adapter as MovieItemAdapter).run {
        addItems(items)
        notifyDataSetChanged()
    }

}