package com.jay.architecturestudy.ui

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jay.architecturestudy.R
import com.jay.architecturestudy.databinding.ActivityWebViewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    private var url: String = ""

    private val viewModel: WebViewViewModel by viewModel { parametersOf(url) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        url = intent.getStringExtra(EXTRA_URL)
            ?: run {
                // 전달된 값이 없으면 activity 종료
                finish()
                return
            }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_web_view)
        binding.webView.run {
            webViewClient = WebViewClient()
            settings.run {
                displayZoomControls = true
                useWideViewPort = true
                javaScriptEnabled = true
                domStorageEnabled = true
            }
        }

        binding.vm = viewModel
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return super.onSupportNavigateUp()
    }

    companion object {
        const val EXTRA_URL = "url"
    }
}