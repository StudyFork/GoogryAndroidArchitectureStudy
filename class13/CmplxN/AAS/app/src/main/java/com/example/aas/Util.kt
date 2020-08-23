package com.example.aas

import android.content.Context
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.View
import android.view.inputmethod.InputMethodManager

fun interpretHtml(str: String): Spanned {
	return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
		Html.fromHtml(str, Html.FROM_HTML_MODE_LEGACY)
	else
		Html.fromHtml(str)
}

fun hideKeyboard(context: Context, view: View) {
	val inputMethod = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
	inputMethod.hideSoftInputFromWindow(view.windowToken, 0)
}