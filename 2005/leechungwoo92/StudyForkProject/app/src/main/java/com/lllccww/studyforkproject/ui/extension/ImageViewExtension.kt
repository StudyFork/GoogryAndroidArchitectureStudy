package com.lllccww.studyforkproject.ui.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.lllccww.studyforkproject.R

@BindingAdapter("loadUrl")
fun ImageView.loadUrl(url: String) {
    Glide.with(this)
        .load(url)
        .error(R.drawable.ic_no_img)
        .into(this)
}