package com.egiwon.architecturestudy.ext

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadUrl(url: String?, block: RequestOptions.() -> RequestOptions) {
    val option = RequestOptions()
    option.block()

    Glide.with(context)
        .load(url)
        .apply(option)
        .into(this)
}

@BindingAdapter("loadUrl", "placeHolder")
fun ImageView.loadUrl(url: String?, placeholder: Drawable) {
    loadUrl(url) {
        placeholder(placeholder)
    }
}
