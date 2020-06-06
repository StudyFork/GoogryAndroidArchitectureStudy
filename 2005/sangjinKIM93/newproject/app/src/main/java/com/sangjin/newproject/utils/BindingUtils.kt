package com.sangjin.newproject.utils

import android.os.Build
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.sangjin.newproject.R


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