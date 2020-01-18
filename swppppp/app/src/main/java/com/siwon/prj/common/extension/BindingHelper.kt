package com.siwon.prj.common.extension

import android.os.Build
import android.text.Html
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("urlImg")
fun ImageView.urlImg(url: String) =
    Glide.with(this)
        .load(url)
        .into(this)

@BindingAdapter("str2floatScore")
fun RatingBar.str2Score(score: String) {
    rating = score.toFloat() / 2f
}

@RequiresApi(Build.VERSION_CODES.N)
@BindingAdapter("rmTag")
fun TextView.rmTag(txt: String) {
    text = txt.formatHtml()
}

@RequiresApi(Build.VERSION_CODES.N)
fun String.formatHtml(): String = Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()