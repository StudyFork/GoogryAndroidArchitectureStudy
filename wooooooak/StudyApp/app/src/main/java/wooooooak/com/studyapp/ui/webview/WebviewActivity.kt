package wooooooak.com.studyapp.ui.webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_webview.*
import wooooooak.com.studyapp.R
import wooooooak.com.studyapp.common.constants.URL

class WebviewActivity : AppCompatActivity() {
    private lateinit var webViewUrl: String
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        webViewUrl = intent?.extras?.getString(URL) ?: kotlin.run {
            finish()
            return
        }
        initWebView()
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) webView.goBack() else super.onBackPressed()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        webView = web_view.apply {
            webViewClient = WebViewClient()
            settings.run {
                displayZoomControls = true
                useWideViewPort = true
                javaScriptEnabled = true
                domStorageEnabled = true
            }
        }
        webView.loadUrl(webViewUrl)
    }
}
