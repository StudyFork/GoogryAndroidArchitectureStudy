package com.hong.architecturestudy.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hong.architecturestudy.R

fun ImageView.loadImageView(imageUrl: String) {
    Glide.with(context)
        .load(imageUrl)
        .apply(RequestOptions.placeholderOf(R.drawable.ic_launcher_background).centerCrop())
        .into(this)
}