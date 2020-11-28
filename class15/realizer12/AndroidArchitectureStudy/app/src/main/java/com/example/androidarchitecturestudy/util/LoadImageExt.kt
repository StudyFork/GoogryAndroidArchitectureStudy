package com.example.androidarchitecturestudy.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.constraintlayout.widget.Placeholder
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("image","error","placeholder")
fun ImageView.loadImage(imageUrl:String,error:Drawable,placeholder: Drawable){
    Glide.with(context)
        .load(imageUrl)
        .error(error)
        .placeholder(placeholder)
        .centerCrop()
        .into(this)
}