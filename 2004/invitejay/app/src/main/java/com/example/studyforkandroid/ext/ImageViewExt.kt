package com.example.studyforkandroid.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter(value = ["loadUrl"])
fun ImageView.loadUrl(url: String?) {
    url?.let {
        Glide.with(this.context)
            .load(url)
            .thumbnail(0.1f)
            .into(this)
    }
}