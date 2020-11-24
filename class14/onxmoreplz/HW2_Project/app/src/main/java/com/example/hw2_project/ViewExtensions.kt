package com.example.hw2_project

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun ImageView.setGlideImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.ic_launcher_foreground)
        .into(this)
}

@BindingAdapter("directorName")
fun TextView.setDirectorName( directorName : String) {
    this.text = directorName.replace("|", "")
}