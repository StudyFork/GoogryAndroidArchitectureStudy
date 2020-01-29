package com.example.handnew04.ui.movie

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
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

    fun getMovieLink(): String {
        //TODO link 는 제대로 넘어오는데 네이버 영화 예매 에서 페이지를 찾을 수 없다고 나옵니다.
        val link = intent.getStringExtra(getString(R.string.movieLink))
        Log.i("왜 페이지를 찾을 수 없는지 모르겠습니다.", link)
        return intent.getStringExtra(getString(R.string.movieLink))
    }

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
