package app.ch.study.ui.detail

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import app.ch.study.R
import app.ch.study.data.common.EXTRA_URL

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val wbDetail: WebView = findViewById(R.id.wb_detail)

        val settings = wbDetail.settings
        settings.javaScriptEnabled = true

        wbDetail.webViewClient = WebViewClient()

        val url = intent.getStringExtra(EXTRA_URL)
        url?.let {
            wbDetail.loadUrl(it)
        }
    }

}
