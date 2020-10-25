package com.hong.architecturestudy.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hong.architecturestudy.R

@BindingAdapter("loadImageUrl")
fun ImageView.loadImageView(imageUrl: String) {
    Glide.with(context)
        .load(imageUrl)
        .apply(RequestOptions.placeholderOf(R.drawable.ic_launcher_background).centerCrop())
        .into(this)
}