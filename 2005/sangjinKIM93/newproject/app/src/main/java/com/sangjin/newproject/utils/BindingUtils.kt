package com.sangjin.newproject.utils

import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sangjin.newproject.R
import com.sangjin.newproject.activity.adapter.MovieListAdapter
import com.sangjin.newproject.data.model.Movie


@BindingAdapter("imageLoader")
fun ImageView.imageLoader(url: String?) {
    url?.let {
        Glide.with(this.context)
            .load(url)
            .placeholder(R.drawable.img_default)
            .centerCrop()
            .into(this)
    }
}

@BindingAdapter("selectLast")
fun EditText.selectLast(keyword: String?) {
    if (!keyword.isNullOrEmpty()) {
        this.setSelection(keyword.length)
    }
}

@BindingAdapter("refreshList")
fun RecyclerView.refreshList(movieList: List<Movie>?) {

    movieList?.let {
        val adapter = this.adapter as MovieListAdapter
        adapter.refreshList(it)
    }
}