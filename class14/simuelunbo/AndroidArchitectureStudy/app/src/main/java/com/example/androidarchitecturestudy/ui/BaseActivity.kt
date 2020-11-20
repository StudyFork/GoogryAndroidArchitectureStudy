package com.example.androidarchitecturestudy.ui

import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseActivity<presenter : BaseContract.Presenter>(@LayoutRes val layout: Int) :
    AppCompatActivity(layout), BaseContract.View {

    override fun hideLoadingBar() {
        progressBar.isVisible = false
    }

    override fun showLoadingBar() {
        progressBar.isVisible = true
    }

    override fun showResultEmpty(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}