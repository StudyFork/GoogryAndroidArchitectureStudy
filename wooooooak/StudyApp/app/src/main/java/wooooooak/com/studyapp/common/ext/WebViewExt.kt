package wooooooak.com.studyapp.common.ext

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.BindingAdapter

@BindingAdapter("webViewUrl")
fun WebView.loadWebView(url: String) {
    webViewClient = WebViewClient()
    settings.run {
        displayZoomControls = true
        useWideViewPort = true
        javaScriptEnabled = true
        domStorageEnabled = true
    }
    loadUrl(url)
}