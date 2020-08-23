package com.example.aas

import android.os.Build
import android.text.Html
import android.text.Spanned

fun interpretHtml(str: String): Spanned {
	return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
		Html.fromHtml(str, Html.FROM_HTML_MODE_LEGACY)
	else
		Html.fromHtml(str)
}