package app.ch.study.view

import android.os.Bundle
import android.webkit.WebViewClient
import app.ch.study.R
import app.ch.study.core.BaseActivity
import app.ch.study.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity<ActivityDetailBinding>() {

    override fun getLayoutResId(): Int = R.layout.activity_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val settings = binding.wbDetail.settings
        settings.javaScriptEnabled = true

        binding.wbDetail.webViewClient = WebViewClient()

        val url = intent.getStringExtra("url")
        url?.let {
            binding.wbDetail.loadUrl(it)
        }
    }

}
