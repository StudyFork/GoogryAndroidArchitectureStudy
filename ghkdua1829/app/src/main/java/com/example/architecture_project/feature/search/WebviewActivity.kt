package com.example.architecture_project.feature.search

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.architecture_project.R

class WebviewActivity : AppCompatActivity() {

    companion object{
        private const val URL="url"
    }

    lateinit var wvMovie: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        wvMovie = findViewById(R.id.wb_page)
        wvMovie.webViewClient = WebViewClient()
        var webViewSettings = wvMovie.settings
        webViewSettings.javaScriptEnabled = true
        webViewSettings.setSupportMultipleWindows(false)
        webViewSettings.useWideViewPort = true
        webViewSettings.setSupportZoom(false)
        wvMovie.loadUrl(intent.getStringExtra(URL))
    }
}
