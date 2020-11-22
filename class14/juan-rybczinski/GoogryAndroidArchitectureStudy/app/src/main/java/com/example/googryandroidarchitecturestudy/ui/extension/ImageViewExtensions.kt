package com.example.googryandroidarchitecturestudy.ui.extension

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl", "placeholder", "error", requireAll = false)
fun ImageView.loadImage(url: String?, placeholder: Drawable?, error: Drawable?) {
    Glide.with(context)
        .load(url)
        .placeholder(placeholder)
        .error(error)
        .into(this)
}