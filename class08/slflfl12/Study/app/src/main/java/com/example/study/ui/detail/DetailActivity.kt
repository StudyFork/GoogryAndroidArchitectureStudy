package com.example.study.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.study.R

import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var intent = getIntent()
        var movieUrl = intent.getStringExtra(MOVIE_URL)

        wv_detail.settings.javaScriptEnabled
        movieUrl?.let {
            showDetailView(movieUrl)
        }

    }

    override fun showDetailView(movieUrl: String) {
        wv_detail.loadUrl(movieUrl)
    }

    companion object {
        const val MOVIE_URL = "movieUrl"
    }
}