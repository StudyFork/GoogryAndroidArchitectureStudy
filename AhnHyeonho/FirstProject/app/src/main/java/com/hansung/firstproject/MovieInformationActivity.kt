package com.hansung.firstproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_movie_information.*

class MovieInformationActivity : AppCompatActivity() {

    private lateinit var webPageUrl: String

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_information)
        webPageUrl = intent.getStringExtra("WEB_PAGE")

        with(movieInformationWebView) {
            webViewClient = WebViewClient()
            loadUrl(webPageUrl)
            with(settings) {
                setSupportMultipleWindows(false)
                useWideViewPort = true
                setSupportZoom(false)
                javaScriptEnabled = true
            }
        }
    }
}
