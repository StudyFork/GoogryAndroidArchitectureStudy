package com.example.architecturestudy.ui

import android.os.Bundle
import android.util.Log
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.architecturestudy.R
import kotlinx.android.synthetic.main.activity_webview.*

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        val url = intent.getStringExtra(EXTRA_URL)

        val webView = webview.apply {
            webViewClient = WebViewClient()
            settings.run {
                useWideViewPort = true
                javaScriptEnabled = true
                domStorageEnabled = true
                displayZoomControls = true
            }
        }
        Log.i("들어온 url","url : ${url}")
        webView.loadUrl(url)
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return super.onSupportNavigateUp()
    }

    companion object {
        const val EXTRA_URL = "url"
    }
}