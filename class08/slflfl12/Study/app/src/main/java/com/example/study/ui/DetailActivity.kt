package com.example.study.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.study.R

import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var intent = getIntent()
        var url = intent.getStringExtra("url")

        webview.settings.javaScriptEnabled
        webview.loadUrl(url)
    }
}