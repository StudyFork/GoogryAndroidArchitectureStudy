package com.example.archstudy.ext

import android.widget.RatingBar
import androidx.databinding.BindingAdapter

@BindingAdapter("rating")
fun RatingBar.ratingValueBindingAdapter(value : String){
    rating = value.toFloat() / 2F
}