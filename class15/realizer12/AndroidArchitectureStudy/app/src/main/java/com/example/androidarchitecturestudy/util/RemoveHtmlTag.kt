package com.example.androidarchitecturestudy.util

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("htmlText")
fun TextView.removeHtmlTag(htmlText:String){
    text = htmlText?.replace("<b>", "")?.replace("</b>", "") ?: ""
}