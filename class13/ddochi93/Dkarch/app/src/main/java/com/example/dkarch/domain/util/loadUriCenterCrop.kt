package com.example.dkarch.domain.util

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadUriCenterCrop(uri: Uri) {
    val options = RequestOptions()
    GlideApp.with(this).load(uri).apply(options.centerCrop()).into(this)
}

fun ImageView.loadUrlCenterCrop(url: String) {
    val options = RequestOptions()
    GlideApp.with(this).load(url).apply(options.centerCrop()).into(this)
}

fun ImageView.loadUri(uri: Uri) {
    GlideApp.with(this).load(uri).into(this)
}

fun ImageView.loadUrl(url: String?) {
    url?.let {
        GlideApp.with(this).load(it).into(this)
    }
}

fun ImageView.loadDrawable(drawable: Drawable) {
    GlideApp.with(this).load(drawable).into(this)
}
