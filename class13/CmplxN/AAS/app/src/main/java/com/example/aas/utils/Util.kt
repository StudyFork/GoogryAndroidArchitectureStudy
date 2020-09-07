package com.example.aas.utils

import android.content.Context
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

fun String.toHtml(): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    else
        Html.fromHtml(this)
}

fun hideKeyboard(context: Context, view: View) {
    val inputMethod = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethod.hideSoftInputFromWindow(view.windowToken, 0)
}

fun ImageView.glideCenterCrop(uri: String) {
    Glide.with(this)
        .load(uri)
        .centerCrop()
        .into(this)
}

fun Context.showToast(message: String, duration: Int) {
    Toast.makeText(this, message, duration).show()
}