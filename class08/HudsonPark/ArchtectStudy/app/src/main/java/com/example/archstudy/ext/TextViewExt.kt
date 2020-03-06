package com.example.archstudy.ext

import android.os.Build
import android.text.Html
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("html")
fun TextView.convertHtmlBindingAdapter(htmlText : String){
    // Android N Version 이상의 경우
    text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY).toString()
    } else {
        // 그 외
        Html.fromHtml(htmlText).toString()
    }
}