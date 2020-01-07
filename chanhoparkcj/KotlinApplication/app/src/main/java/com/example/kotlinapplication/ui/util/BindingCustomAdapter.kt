package com.example.kotlinapplication.ui.util

import android.widget.TextView
import androidx.databinding.BindingAdapter

object BindingCustomAdapter {

    @BindingAdapter("setFormat")
    fun setFormat(view: TextView , msg : String){
        view.text = msg
    }
}