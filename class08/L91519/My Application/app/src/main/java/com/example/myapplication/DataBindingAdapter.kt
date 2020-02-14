package com.example.myapplication

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.data.model.MovieResult
import com.example.myapplication.ui.MovieRecyclerViewAdpater

@BindingAdapter("bindImage")
fun bindImage(view: ImageView, url: String?) {
    url.let {
        Glide.with(view)
            .load(it)
            .into(view)
    }
}

@BindingAdapter("bindRatingStar")
fun bindingRatingStar(view: RatingBar, userRating: Float?) {
    if (userRating != null) {
        view.rating = (userRating / 2)
    }
}

@BindingAdapter("setOnClick")
fun View.setMovieItemClickListener(link: String?) {
    setOnClickListener {
        link?.let {
        }
    }
}

//@BindingAdapter("bind:bindSetMovieItem")
@BindingAdapter("bindSetMovieItem")
fun RecyclerView.bindSetMovieItem(items: List<MovieResult.Item>?) {
    if (adapter is MovieRecyclerViewAdpater)
        items?.let {
            (adapter as MovieRecyclerViewAdpater).setItems(it)
        }
}