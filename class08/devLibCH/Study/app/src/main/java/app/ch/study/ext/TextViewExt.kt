package app.ch.study.ext

import android.os.Build
import android.text.Html
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["htmlText"])
fun TextView.setHtmlText(source: String) {
    text = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N)
        Html.fromHtml(source)
    else
        Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY)
}