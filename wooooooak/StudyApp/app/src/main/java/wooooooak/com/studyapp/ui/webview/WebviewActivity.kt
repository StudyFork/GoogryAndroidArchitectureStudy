package wooooooak.com.studyapp.ui.webview

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import wooooooak.com.studyapp.R
import wooooooak.com.studyapp.common.constants.URL
import wooooooak.com.studyapp.databinding.ActivityWebviewBinding

class WebviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val webViewUrl = intent?.extras?.getString(URL) ?: kotlin.run {
            finish()
            return
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_webview)
        binding.url = webViewUrl
        binding.executePendingBindings()
    }

    override fun onBackPressed() {
        val webView = binding.root as WebView
        if (webView.canGoBack()) webView.goBack() else super.onBackPressed()
    }

}
