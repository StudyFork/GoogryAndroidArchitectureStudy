package com.onit.googlearchitecturestudy.ui.movieInformation

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.onit.googlearchitecturestudy.R
import kotlinx.android.synthetic.main.activity_movie_information.*

class MovieInformationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movie_information)

        init()
    }

    private fun init() {
        movieInformationWebView.webViewClient = WebViewClient()
        movieInformationWebView.loadUrl(intent.getStringExtra(MOVIE_URL))
        with(movieInformationWebView.settings) {
            setSupportMultipleWindows(false)
            useWideViewPort = true
            setSupportZoom(false)
            setSupportMultipleWindows(false)
            javaScriptEnabled = true
        }
    }

    companion object {
        const val MOVIE_URL = "movieURL"
    }
}
