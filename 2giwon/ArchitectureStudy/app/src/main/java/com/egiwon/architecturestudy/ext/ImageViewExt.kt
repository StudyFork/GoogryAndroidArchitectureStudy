package com.egiwon.architecturestudy.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadAsync(url: String?, block: RequestOptions.() -> RequestOptions) =
    Glide.with(context)
        .load(url)
        .apply(block.invoke(RequestOptions()))
        .into(this)

