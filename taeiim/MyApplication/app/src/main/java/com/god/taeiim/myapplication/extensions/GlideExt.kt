package com.god.taeiim.myapplication.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.god.taeiim.myapplication.R

fun ImageView.loadImage(url: String?) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.ic_photo)
        .error(R.drawable.ic_photo)
        .into(this)
}
