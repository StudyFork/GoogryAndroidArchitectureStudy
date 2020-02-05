package com.example.handnew04.adapter

import android.os.Build
import android.text.Html
import android.text.Spanned
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.handnew04.data.MovieData

@BindingAdapter("bind:bindImage")
fun bindImage(imageView: ImageView, imageUri: String) {
    Glide.with(imageView.context).load(imageUri).into(imageView)
}

@BindingAdapter("bind:bindRatingString")
fun bindRatingString(ratingBar: RatingBar, rating: String) {
    ratingBar.rating = rating.toFloat()
}

@BindingAdapter("android:text")
fun setText(textView: TextView, text: CharSequence?) {
    val oldText: CharSequence = textView.text
    if (text === oldText || text == null && oldText.isEmpty()) {
        return
    }
    if (text is Spanned) {
        if (text == oldText) {
            return
        }
    } else if (!haveContentsChanged(text, oldText)) {
        return
    }

    val htmlText: String = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(text as String, Html.FROM_HTML_MODE_LEGACY).toString()
    } else {
        Html.fromHtml(text as String).toString()
    }

    textView.setText(htmlText)
}

fun haveContentsChanged(
    str1: CharSequence?,
    str2: CharSequence?
): Boolean {
    if (str1 == null != (str2 == null)) {
        return true
    } else if (str1 == null) {
        return false
    }
    val length = str1.length
    if (length != str2!!.length) {
        return true
    }
    for (i in 0 until length) {
        if (str1[i] != str2[i]) {
            return true
        }
    }
    return false
}
