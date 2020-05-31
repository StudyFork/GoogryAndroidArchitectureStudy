package com.hwaniiidev.architecture.ui.main

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.hwaniiidev.architecture.R

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view)
        .load(url)
        .error(R.drawable.gm_noimage)
        .into(view)
}
