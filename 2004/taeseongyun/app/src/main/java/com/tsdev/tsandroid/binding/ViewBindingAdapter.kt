package com.tsdev.tsandroid.binding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
fun View.visibleBindingAdapter(isLoading: Boolean) {
    visibility = if (isLoading)
        View.VISIBLE
    else
        View.GONE
}