package com.example.study.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.study.R
import com.example.study.data.model.Movie

import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var intent = getIntent()
        var movie_url = intent.getStringExtra(MOVIE_URL)

        webview.settings.javaScriptEnabled
        movie_url?.let {
            webview.loadUrl(it)
        }
    }
    companion object {
        const val MOVIE_URL = "movie_url"
    }
}