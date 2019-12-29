package com.egiwon.architecturestudy.ext

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("setTextHtml")
fun TextView.setTextHtml(text: String) {
    setText(text.fromHtml())
}
