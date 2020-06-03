package com.hwaniiidev.architecture.ui.main

import android.os.Build
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.hwaniiidev.architecture.R

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view)
        .load(url)
        .error(R.drawable.gm_noimage)
        .into(view)
}

@BindingAdapter("htmlTitle")
fun loadHtml(view: TextView, title: String) {
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
        view.text = Html.fromHtml(title,Html.FROM_HTML_MODE_LEGACY)
    }else{
        view.text = Html.fromHtml(title)
    }

}
