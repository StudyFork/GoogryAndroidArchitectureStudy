package com.example.archstudy.ext

import android.widget.RatingBar
import androidx.databinding.BindingAdapter

@BindingAdapter("rating")
fun ratingValueBindingAdapter(view : RatingBar ,value : String){
    view.rating = value.toFloat() / 2F
}