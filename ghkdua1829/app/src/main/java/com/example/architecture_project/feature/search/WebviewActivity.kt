package com.example.architecture_project.feature.search

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.architecture_project.R
import com.example.architecture_project.`object`.ObjectCollection.URL
import com.example.architecture_project.databinding.ActivityWebviewBinding

class WebviewActivity : AppCompatActivity() {


    lateinit var wvMovie: WebView
    lateinit var binding: ActivityWebviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_webview)
        setContentView(R.layout.activity_webview)

        binding.wbPage.webViewClient = WebViewClient()
        val webViewSettings = binding.wbPage.settings
        webViewSettings.run {
            javaScriptEnabled = true
            setSupportMultipleWindows(false)
            useWideViewPort = true
            setSupportZoom(false)
        }
        binding.wbPage.loadUrl(intent.getStringExtra(URL))
    }
}
