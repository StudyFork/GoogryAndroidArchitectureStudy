package com.example.architecture_project.feature.search

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.architecture_project.R
import com.example.architecture_project.`object`.ObjectCollection.URL

class WebviewActivity : AppCompatActivity() {


    lateinit var wvMovie: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        wvMovie = findViewById(R.id.wb_page)
        wvMovie.webViewClient = WebViewClient()
        var webViewSettings = wvMovie.settings
        webViewSettings.run {
            javaScriptEnabled=true
            setSupportMultipleWindows(false)
            useWideViewPort = true
            setSupportZoom(false)
        }
        wvMovie.loadUrl(intent.getStringExtra(URL))
    }
}
