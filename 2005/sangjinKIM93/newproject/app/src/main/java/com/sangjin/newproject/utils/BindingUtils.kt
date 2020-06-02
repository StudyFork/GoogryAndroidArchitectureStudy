package com.sangjin.newproject.utils

import android.os.Build
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.sangjin.newproject.R

@BindingAdapter("htmlToSpanned")
fun TextView.htmlToSpanned(title: String?) {
    title?.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            text = Html.fromHtml(it, Html.FROM_HTML_MODE_LEGACY)
        } else {
            text = Html.fromHtml(it)
        }
    }
}

@BindingAdapter("imageLoader")
fun ImageView.imageLoader(url: String?){
    url?.let {
        Glide.with(this.context)
            .load(url)
            .placeholder(R.drawable.img_default)
            .centerCrop()
            .into(this)
    }
}