package com.example.study.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.study.R
import com.example.study.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity(), DetailContract.View {

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        var intent = getIntent()
        var movieUrl = intent.getStringExtra(MOVIE_URL)

        binding.wvDetail.settings.javaScriptEnabled
        movieUrl?.let {
            showDetailView(movieUrl)
        }
    }

    override fun showDetailView(movieUrl: String) {
        binding.wvDetail.loadUrl(movieUrl)
    }

    companion object {
        const val MOVIE_URL = "movieUrl"
    }
}