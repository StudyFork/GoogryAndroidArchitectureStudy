package com.hansung.firstproject.ui.movieinformation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hansung.firstproject.R
import com.hansung.firstproject.databinding.ActivityMovieInformationBinding

class MovieInformationActivity : AppCompatActivity() {

    private lateinit var webPageUrl: String

    private lateinit var binding: ActivityMovieInformationBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_information)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_information)

        readUrl()
        loadWebView()
    }

    private fun readUrl() {
        webPageUrl = intent.getStringExtra(TAG)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadWebView() {

        with(binding.movieInformationWebView) {
            webViewClient = WebViewClient()
            loadUrl(webPageUrl)
            with(settings) {
                setSupportMultipleWindows(false)
                useWideViewPort = true
                setSupportZoom(false)
                javaScriptEnabled = true
            }
        }
    }

    companion object {
        const val TAG = "WEB_PAGE"
        fun getIntent(context: Context): Intent {
            return Intent(context, MovieInformationActivity::class.java)
        }
    }
}
