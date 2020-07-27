package com.example.architecturestudy.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImageWithUrl")
fun loadImageWithUrl(imageView: ImageView, imgUrl: String) {
    Glide.with(imageView).load(imgUrl).into(imageView)
}