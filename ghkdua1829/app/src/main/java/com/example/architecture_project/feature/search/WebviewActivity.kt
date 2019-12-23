package com.example.architecture_project.feature.search

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.architecture_project.R

class WebviewActivity : AppCompatActivity() {

    lateinit var wv_movie: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        wv_movie = findViewById(R.id.wb_page)
        wv_movie.webViewClient = WebViewClient()
        var webview_settings = wv_movie.settings
        webview_settings.javaScriptEnabled = true
        webview_settings.setSupportMultipleWindows(false)
        webview_settings.useWideViewPort = true
        webview_settings.setSupportZoom(false)
        wv_movie.loadUrl(intent.getStringExtra("url"))
    }
}
