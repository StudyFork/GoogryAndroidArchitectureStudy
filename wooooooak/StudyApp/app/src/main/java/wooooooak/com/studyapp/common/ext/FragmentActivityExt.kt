package wooooooak.com.studyapp.common.ext

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import wooooooak.com.studyapp.common.constants.URL
import wooooooak.com.studyapp.ui.webview.WebviewActivity

fun FragmentActivity.startWebView(url: String) {
    val intent = Intent(this, WebviewActivity::class.java).apply {
        putExtra(URL, url)
    }
    startActivity(intent)
}