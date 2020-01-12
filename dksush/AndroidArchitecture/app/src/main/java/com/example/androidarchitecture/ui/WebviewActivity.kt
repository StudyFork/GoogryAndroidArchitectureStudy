package com.example.androidarchitecture.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.androidarchitecture.R
import com.example.androidarchitecture.common.StringConst.Companion.INTENT_KEY_LINK
import com.example.androidarchitecture.databinding.ActivityWebviewBinding

class WebviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebviewBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_webview)


        val url = intent.getStringExtra(INTENT_KEY_LINK)
            ?: run {
                finish()
                return
            }

        binding.webview.apply {
            webViewClient = WebViewClient()
            settings.run {
                javaScriptEnabled = true
                domStorageEnabled = true
            }
        }

        binding.webview.loadUrl(url)

    }

}
