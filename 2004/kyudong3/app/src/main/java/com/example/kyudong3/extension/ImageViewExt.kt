package com.example.kyudong3.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter(value = ["loadUrl"])
fun ImageView.loadUrl(url: String?) {
    url?.let {
        Glide.with(this.context)
            .load(url)
            .into(this)
    }
}

