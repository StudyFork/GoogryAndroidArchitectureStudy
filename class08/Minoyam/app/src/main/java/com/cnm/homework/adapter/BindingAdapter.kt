package com.cnm.homework.adapter

import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cnm.homework.data.model.NaverResponse

@BindingAdapter("bind:bindImage")
fun bindImage(imageView: ImageView, imageUri: String?) {
    imageUri?.let {
        Glide.with(imageView.context).load(imageUri).into(imageView)
    }

}

@BindingAdapter("bind:bindRating")
fun bindRating(ratingBar: RatingBar, rating: Float) {
    ratingBar.rating = rating / 2
}

@BindingAdapter("bind:bindOnEditorActionListener")
fun bindOnEditorActionListener(editText: EditText, click: (() -> Unit)) {
    editText.setOnEditorActionListener { _, i, _ ->
        when (i) {
            EditorInfo.IME_ACTION_SEARCH -> {
                click()
            }
        }
        true
    }
}

@BindingAdapter("bind:bindSetMovieItem")
fun RecyclerView.bindSetMovieItem(items: List<NaverResponse.Item>?) {
    if (adapter is MovieAdapter)
        items?.let {
            (adapter as MovieAdapter).setItem(it)
        }
}