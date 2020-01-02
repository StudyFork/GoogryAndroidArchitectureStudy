package com.example.androidstudy.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("setImageId")
fun setImageResource(v: ImageView, resId: Int?){
    resId.let {
        Glide
            .with(v.context)
            .load(it)
            .centerCrop()
            .into(v)
    }
}