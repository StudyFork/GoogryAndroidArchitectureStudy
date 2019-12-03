package com.example.androidarchitecture.ui

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.androidarchitecture.R
import kotlinx.android.synthetic.main.activity_webview.*

class WebviewActivity : AppCompatActivity() {


    private lateinit var url: String
    private lateinit var Webview: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)


        url = intent.getStringExtra("link")
            ?: run {
                finish()
                return
            }


        Webview = webview.apply {
            webViewClient = WebViewClient()
            settings.run {
                javaScriptEnabled = true
                domStorageEnabled = true
            }
        }

        Webview.loadUrl(url)


    }

}
