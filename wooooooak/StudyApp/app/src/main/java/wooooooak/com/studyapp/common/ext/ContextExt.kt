package wooooooak.com.studyapp.common.ext

import android.content.Context
import android.content.Intent
import android.widget.Toast
import wooooooak.com.studyapp.common.constants.URL
import wooooooak.com.studyapp.ui.webview.WebviewActivity

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.startWebView(url: String) {
    val intent = Intent(this, WebviewActivity::class.java).apply {
        putExtra(URL, url)
    }
    startActivity(intent)
}