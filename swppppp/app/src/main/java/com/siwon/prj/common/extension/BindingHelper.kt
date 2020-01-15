package com.siwon.prj.common.extension

import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
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

@BindingAdapter("rmTag")
fun AppCompatTextView.rmTag(txt: String) {
    text = txt.replace("<b>", "[").replace("</b>", "]")
}

@BindingAdapter("rmOr")
fun TextView.rmOr(txt: String) {
    text = txt.replace("|", ", ")
}
