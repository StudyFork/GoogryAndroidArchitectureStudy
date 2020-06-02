package com.example.architecture.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.architecture.R
import com.example.architecture.util.ConstValue.Companion.NO_IMAGE_URL

@BindingAdapter("android:imageSrc")
fun ImageView.loadImage(url: String?) {

    if (url == null) return

    Glide.with(this)
        .load(url.ifBlank { NO_IMAGE_URL })
        .placeholder(R.drawable.ic_loading_black_24dp)
        .error(R.drawable.image_loaderror)
        .centerCrop()
        .into(this)

}
