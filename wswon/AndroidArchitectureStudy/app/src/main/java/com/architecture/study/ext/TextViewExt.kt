package com.architecture.study.ext

import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("compareYesterdayColor")
fun TextView.compareYesterdayColor(colorResId: Int){
    setTextColor(ContextCompat.getColor(context, colorResId))
}