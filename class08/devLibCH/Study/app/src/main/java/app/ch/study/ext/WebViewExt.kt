package app.ch.study.ext

import android.webkit.WebView
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["loadDataUrl"])
fun WebView.loadDataUrl(url: String?) {
    url?.let {
        loadUrl(url)
    }
}

@BindingAdapter(value = ["javaScriptEnable"])
fun WebView.setJavaScriptEnabled(isEnabled: Boolean) {
    settings.javaScriptEnabled = isEnabled
}
