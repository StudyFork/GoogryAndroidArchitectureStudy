package com.siwon.prj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_detail_webview.*

class DetailWebview : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_webview)

        val webview = webview
        webview.webViewClient = WebViewClient()
        var webviewSetting = webview.settings
        webviewSetting.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            setSupportMultipleWindows(false)
            javaScriptCanOpenWindowsAutomatically = false
            layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        }
        webview.loadUrl(intent.getStringExtra("link"))
    }
}
