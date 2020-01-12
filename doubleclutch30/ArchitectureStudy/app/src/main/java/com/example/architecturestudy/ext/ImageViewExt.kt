package com.example.architecturestudy.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.architecturestudy.R

@BindingAdapter("loadUrl")
fun ImageView?.setUrl(url: String?) {
    if (this != null) {
        Glide.with(this)
            .load(url)
            .error(R.drawable.ic_launcher_background)
            .into(this)
    }
}