package com.studyfork.architecturestudy.base

import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<P : BasePresenter> : AppCompatActivity(), BaseContract.View {

    abstract val presenter: P
    abstract val progressBar: ProgressBar

    override fun onDestroy() {
        presenter.clearDisposable()
        super.onDestroy()
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }
}