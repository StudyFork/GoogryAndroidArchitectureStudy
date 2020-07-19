package com.world.tree.architecturestudy

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, url:String) {
    Glide.with(imageView).load(url)
        .centerCrop()
        .into(imageView)
}