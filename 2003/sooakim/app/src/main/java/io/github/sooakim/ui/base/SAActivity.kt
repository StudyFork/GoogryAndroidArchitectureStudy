package io.github.sooakim.ui.base

import android.annotation.SuppressLint
import android.view.View
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("Registered")
open class SAActivity : AppCompatActivity() {
    protected open val commonProgressView: View? = null

    fun showLoading() {
        commonProgressView?.visibility = View.VISIBLE
    }

    fun hideLoading() {
        commonProgressView?.visibility = View.INVISIBLE
    }
}