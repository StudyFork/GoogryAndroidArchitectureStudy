package com.practice.achitecture.myproject.main

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import common.GlideWrapper

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("bind:setImageUrl")
    fun showImage(view: ImageView, url: String) {
        GlideWrapper.showImage(view, url)
    }

}