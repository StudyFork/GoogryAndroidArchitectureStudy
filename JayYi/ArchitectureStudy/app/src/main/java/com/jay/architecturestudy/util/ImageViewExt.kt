package com.jay.architecturestudy.util

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImage")
fun ImageView.loadImage(url: String) {
    try {
        Glide.with(this)
            .load(url)
            .into(this)
    } catch (e: Exception) {
        Log.e("ImageViewExt", "error=${e.message}")
    }
}