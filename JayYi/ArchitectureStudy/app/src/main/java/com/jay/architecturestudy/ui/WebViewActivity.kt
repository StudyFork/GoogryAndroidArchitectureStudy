package com.jay.architecturestudy.ui

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.jay.architecturestudy.R
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val url = intent.getStringExtra(EXTRA_URL)
            ?: run {
                // 전달된 값이 없으면 activity 종료
                finish()
                return
            }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val webView = web_view.apply {
            webViewClient = WebViewClient()
            settings.run {
                displayZoomControls = true
                useWideViewPort = true
                javaScriptEnabled = true
                domStorageEnabled = true
            }
        }
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