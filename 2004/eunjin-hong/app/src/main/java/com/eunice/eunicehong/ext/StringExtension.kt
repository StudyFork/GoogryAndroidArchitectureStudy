package com.eunice.eunicehong.ext

import android.text.Spanned
import androidx.core.text.HtmlCompat


fun String.parseHtml(): Spanned =
    HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)
