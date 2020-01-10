package com.hansung.firstproject.ui.movieinformation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.hansung.firstproject.R
import kotlinx.android.synthetic.main.activity_movie_information.*

class MovieInformationActivity : AppCompatActivity(), MovieInformationContract.View {

    private lateinit var presenter: MovieInformationContract.Presenter

    private lateinit var webPageUrl: String

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_information)
        presenter = MovieInformationPresenter(this).also { it.readUrl() }

        presenter.loadWebView()
    }

    override fun readUrl() {
        webPageUrl = intent.getStringExtra(TAG)
    }

    override fun loadWebView() {
        with(movieInformationWebView) {
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
