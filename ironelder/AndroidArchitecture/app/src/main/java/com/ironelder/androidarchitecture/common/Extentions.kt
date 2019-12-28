package com.ironelder.androidarchitecture.common

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ironelder.androidarchitecture.R

@BindingAdapter("setImageViewVisible")
fun setImageViewVisible(view: ImageView, url:String?){
    if(url.isNullOrEmpty()){
        view.visibility = View.GONE
    } else {
        view.visibility = View.VISIBLE
    }
}

@BindingAdapter("loadImageUrl")
fun loadImageUrl(view: ImageView, url: String?) {
    if(!url.isNullOrEmpty()){
        Glide.with(view.context).load(url).centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background).into(view)
    }
}