package com.practice.achitecture.myproject.base

import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseActivity<P : BaseContract.Presenter>(@LayoutRes contentLayoutId: Int) :
    AppCompatActivity(contentLayoutId), BaseContract.View {

    abstract val presenter: P

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showToast(stringResId: Int) {
        showToast(this.getString(stringResId))
    }

    override fun showLoading() {
        progress_bar?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress_bar?.visibility = View.GONE
    }
}