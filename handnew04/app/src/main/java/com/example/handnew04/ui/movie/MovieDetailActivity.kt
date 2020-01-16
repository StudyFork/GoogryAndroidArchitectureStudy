package com.example.handnew04.ui.movie

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.handnew04.R
import com.example.handnew04.databinding.ActivityMovieDetailBinding

class MovieDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityMovieDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this@MovieDetailActivity, R.layout.activity_movie_detail)
        supportActionBar?.hide()
        initWebView()
    }

    fun getMovieLink(): String? = intent.getStringExtra(getString(R.string.movieLink))

    @SuppressLint("ObsoleteSdkInt", "SetJavaScriptEnabled")
    private fun initWebView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true)
        }

        binding.wbMovieDetail.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }

        val settings = binding.wbMovieDetail.settings
        settings.javaScriptEnabled = true

        binding.wbMovieDetail.loadUrl(getMovieLink())
    }
}
