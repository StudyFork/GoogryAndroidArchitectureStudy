package com.example.androidarchitecture.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.androidarchitecture.R
import kotlinx.android.synthetic.main.activity_webview.*

class WebviewActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)


        val url = intent.getStringExtra("link")
            ?: run {
                finish()
                return
            }

        webview.apply {
            webViewClient = WebViewClient()
            settings.run {
                javaScriptEnabled = true
                domStorageEnabled = true
            }
        }

        webview.loadUrl(url)

    }

}
