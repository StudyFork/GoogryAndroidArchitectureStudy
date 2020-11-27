package com.jay.aas.binding

import android.os.Build
import android.text.Html
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("onEditorEnterAction")
fun EditText.onEditorEnterAction(action: (() -> Unit)? = null) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            action?.invoke()
            return@setOnEditorActionListener true
        }
        return@setOnEditorActionListener false
    }
}

@Suppress("DEPRECATION")
@BindingAdapter("htmlText")
fun TextView.htmlText(text: String) {
    this.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(text)
    }
}

@BindingAdapter("loadUrl")
fun ImageView.loadUrl(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}

@BindingAdapter("isVisible")
fun View.isVisible(condition: Boolean) {
    isVisible = condition
}