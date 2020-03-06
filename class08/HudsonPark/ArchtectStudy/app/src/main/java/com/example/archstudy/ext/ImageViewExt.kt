package com.example.archstudy.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.archstudy.R

@BindingAdapter("image")
fun ImageView.imageViewBindingAdapter(imageView: ImageView, image: String) {

    Glide.with(context)
        .load(image)
        .override(600,1000)
        .error(R.drawable.no_image)
        .into(imageView)
}
