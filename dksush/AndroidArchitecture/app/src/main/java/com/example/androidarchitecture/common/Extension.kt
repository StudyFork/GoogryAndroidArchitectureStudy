package com.example.androidarchitecture.common

import android.annotation.SuppressLint
import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@SuppressLint("ShowToast")
fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

}

@BindingAdapter("htmlText")
fun TextView.changeHtmlText(text: String) {
    this.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_COMPACT)
}

@BindingAdapter("bindImage")
fun bindImage(view: ImageView, res: String) {
    Glide.with(view.context)
        .load(res)
        .into(view)
}
