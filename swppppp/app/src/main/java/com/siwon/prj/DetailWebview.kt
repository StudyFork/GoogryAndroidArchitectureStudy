package com.siwon.prj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_detail_webview.*

class DetailWebview : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_webview)

//        val webview = webview -->필요없어..
        // scope 함수 이용해서 더ㅓ 깔끔하게 가능
        with(webview){
            webViewClient = WebViewClient()
            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                setSupportMultipleWindows(false)
                javaScriptCanOpenWindowsAutomatically = false
                layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
            }
            loadUrl(intent.getStringExtra("link"))
        }
    }
}
