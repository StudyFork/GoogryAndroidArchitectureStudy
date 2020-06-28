package com.example.architecture.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.architecture.R
import com.example.architecture.util.ConstValue.NO_IMAGE_URL

@BindingAdapter("android:imageSrc")
fun ImageView.loadImage(url: String?) {

    url?.let {
        Glide.with(this)
            .load(it.ifBlank { NO_IMAGE_URL })
            .placeholder(R.drawable.ic_loading_black_24dp)
            .error(R.drawable.image_loaderror)
            .centerCrop()
            .into(this)
    }
}
