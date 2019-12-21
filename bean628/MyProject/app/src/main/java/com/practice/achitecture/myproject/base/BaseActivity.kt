package com.practice.achitecture.myproject.base

import android.view.View
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.practice.achitecture.myproject.R
import com.practice.achitecture.myproject.makeToast

abstract class BaseActivity<P : BaseContract.Presenter>(@LayoutRes contentLayoutId: Int) :
    AppCompatActivity(contentLayoutId), BaseContract.View {

    abstract val presenter: P
    private var progressBar: ProgressBar = this.findViewById(R.id.progress_bar)

    override fun showToast(message: String) {
        this.makeToast(message)
    }

    override fun showToast(stringResId: Int) {
        this.makeToast(stringResId)
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }
}