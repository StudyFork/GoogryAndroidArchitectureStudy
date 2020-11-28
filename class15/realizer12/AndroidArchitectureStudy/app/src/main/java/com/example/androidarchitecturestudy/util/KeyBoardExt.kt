package com.example.androidarchitecturestudy.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.security.cert.Extension

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

//엑티비티 확장용
fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}