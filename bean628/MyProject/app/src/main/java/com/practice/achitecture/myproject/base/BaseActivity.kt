package com.practice.achitecture.myproject.base

import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.practice.achitecture.myproject.makeToast
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseActivity<P : BaseContract.Presenter>(@LayoutRes contentLayoutId: Int) :
    AppCompatActivity(contentLayoutId), BaseContract.View {

    abstract val presenter: P

    override fun showToast(message: String) {
        this.makeToast(message)
    }

    override fun showToast(stringResId: Int) {
        this.makeToast(stringResId)
    }

    override fun showLoading() {
        progress_bar?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress_bar?.visibility = View.GONE
    }
}